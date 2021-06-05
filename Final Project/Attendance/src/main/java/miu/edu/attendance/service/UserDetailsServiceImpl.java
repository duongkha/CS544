package miu.edu.attendance.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import miu.edu.attendance.domain.Address;
import miu.edu.attendance.domain.Admin;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Role;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.domain.User;
import miu.edu.attendance.dto.NewUser;
import miu.edu.attendance.repository.AdminRepository;
import miu.edu.attendance.repository.FacultyRepository;
import miu.edu.attendance.repository.RoleRepository;
import miu.edu.attendance.repository.StudentRepository;
import miu.edu.attendance.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	FacultyRepository facultyRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new UserDetailsImpl(user);
	}

	public User updateProfile(NewUser updateUser){
		User user = userRepository.getUserByUsername(updateUser.getUsername());
		if (user!= null) {
			if(!updateUser.getPassword().isEmpty()){
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				final String encryptedPassword = bCryptPasswordEncoder.encode(updateUser.getPassword());
				user.setPassword(encryptedPassword);
			}

			user.setFirstName(updateUser.getFirstName());
			user.setLastName(updateUser.getLastName());
			user.setPhoneNumber(updateUser.getPhoneNumber());
			userRepository.save(user);
			return user;
		}

		return null;
	}
	
	@Transactional
	public String signUpUser(NewUser newUser) {
		try {
			User u = userRepository.getUserByUsername(newUser.getUsername());
			if (u!= null) {
				return "User name is existing.";
			}
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			final String encryptedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
			User user = new User();
			user.setUsername(newUser.getUsername());
			user.setPassword(encryptedPassword);
			user.setEmail(newUser.getUsername());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setPhoneNumber(newUser.getPhoneNumber());
			Address address= modelMapper.map(newUser.getAddress(), Address.class);
			user.setAddress(address);
			List<Role> roles = roleRepository.findRolesByIdIn(newUser.getRoles().
												stream().map(r->r.getId()).collect(Collectors.toList()));
			user.setRoles(new HashSet<>(roles));
			final User createdUser = userRepository.save(user);
			for(Role role:roles){
				switch ((int) role.getId()){
					case 1:
						Admin admin = new Admin();
						user.setEnabled(true);
						admin.setUser(user);
						admin.setLevel("1");
						adminRepository.save(admin);
						break;
					case 2:
						Faculty faculty = new Faculty();
						faculty.setApproved(false);
						faculty.setUser(user);
						facultyRepository.save(faculty);
						break;
					case 3:
						Student student = new Student();
						student.setStudentId(newUser.getStudentId());
						student.setBarcode(newUser.getBarcodeId());
						user.setEnabled(true);
						student.setUser(user);
						studentRepository.save(student);
						break;
				}
			}
			//userRepository.save(user);

			//final ConfirmationToken confirmationToken = new ConfirmationToken(user);

			//confirmationTokenService.saveConfirmationToken(confirmationToken);
			return "Register successfully.";
		}catch (Exception ex){
			return ex.getMessage();
		}
	}

}