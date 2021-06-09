package miu.edu.attendance.service;


import miu.edu.attendance.domain.*;
import miu.edu.attendance.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import miu.edu.attendance.dto.NewUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;



@Service
@Transactional
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
		User user = userRepository.getUserByusername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new UserDetailsImpl(user);
	}

	public User updateProfile(NewUser updateUser){
		User user = userRepository.getUserByusername(updateUser.getUsername());
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
	

	public String signUpUser(NewUser newUser) {
		try {
			User u = userRepository.getUserByusername(newUser.getUsername());
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
			userRepository.save(user);
			for(Role role:roles){
				switch ((int) role.getId()){
					case 1:
						Admin admin = new Admin();
						user.setEnabled(false);
						admin.setUser(user);
						admin.setLevel("1");
						adminRepository.save(admin);
						break;
					case 2:
						Faculty faculty = new Faculty();
						user.setEnabled(false);
						faculty.setApproved(false);
						faculty.setDepartment(newUser.getDepartment());
						faculty.setUser(user);
						faculty.setFirstName(newUser.getFirstName());
						faculty.setLastName(newUser.getLastName());
						facultyRepository.save(faculty);
						break;
					case 3:
						Student student = new Student();
						student.setStudentId(newUser.getStudentId());
						student.setBarcodeId(newUser.getBarcodeId());
						student.setFirstName(newUser.getFirstName());
						student.setLastName(newUser.getLastName());
						user.setEnabled(false);
						student.setApproved(false);
						student.setUser(user);
						studentRepository.save(student);
						break;
				}
			}

			//final ConfirmationToken confirmationToken = new ConfirmationToken(user);

			//confirmationTokenService.saveConfirmationToken(confirmationToken);
			return "Register successfully.";
		}catch (Exception ex){
			return ex.getMessage();
		}
	}

}