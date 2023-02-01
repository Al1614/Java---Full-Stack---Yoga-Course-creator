package com.alext.yoga.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alext.yoga.models.Course;
import com.alext.yoga.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repo;
	
	public Course findById(Long id) {
		
		Optional<Course> result = repo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
	}
	

	public List<Course> all() {
		return repo.findAll();
	}
	
	public Course create(Course course) {
		return repo.save(course);
	}
	
	public Course updateCourse(Course course) {
		return repo.save(course);
	}
	
	public void deleteCourse(Course course) {
		repo.delete(course);
	}
}

