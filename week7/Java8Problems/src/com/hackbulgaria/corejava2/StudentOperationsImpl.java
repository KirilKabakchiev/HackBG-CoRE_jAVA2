package com.hackbulgaria.corejava2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hackbulgaria.corejava2.data.Gender;
import com.hackbulgaria.corejava2.data.Student;

public class StudentOperationsImpl implements StudentOperations{

	private List<Student> students;
	
	public StudentOperationsImpl(List<Student> s){
		students = s;
	}
	
	@Override
	public double getAverageMark() {
		
		return students.stream().mapToDouble(s -> s.getGrade()).average().getAsDouble();
	}

	@Override
	public List<Student> getAllPassing() {
		return students.stream().filter(s -> s.getGrade() >= 3).collect(Collectors.toList());
	}

	@Override
	public List<Student> getAllFailing() {
		return students.stream().filter(s -> s.getGrade() == 2).collect(Collectors.toList());
	}

	@Override
	public Map<Boolean, List<Student>> splitStudentsByMarks(float splitMark) {
		
		Map<Boolean, List<Student>> collect = students.stream().collect(
		() -> {
			Map<Boolean, List<Student>> splitMap = new HashMap<>();
			splitMap.put(true, new ArrayList<Student>());
			splitMap.put(false, new ArrayList<Student>());
			return splitMap;
		},
		
		(splitMap, s) -> {
			if(s.getGrade() >= splitMark){
				splitMap.get(true).add(s);
			}
			else {
				splitMap.get(false).add(s);
			}
		},
		
		(splitMap1, splitMap2) -> {
			splitMap1.get(true).addAll(splitMap2.get(true));
			splitMap1.get(false).addAll(splitMap2.get(false));
		});
		
		return collect;
	}

	@Override
	public List<Student> orderByMarkDescending() {
		Comparator<Student> byStudentGrade = (s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade());
		return students.stream().sorted(byStudentGrade.reversed()).collect(Collectors.toList());
	}

	@Override
	public List<Student> orderByMarkAscending() {
		Comparator<Student> byStudentGrade = (s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade());
		return students.stream().sorted(byStudentGrade).collect(Collectors.toList());
	}

	@Override
	public List<Student> getStudentsWithLowestMarks() {
		 Student student = orderByMarkAscending().stream().findFirst().get();
		 return students.stream().filter(s -> s.getGrade() == student.getGrade()).collect(Collectors.toList());
	}

	@Override
	public List<Student> getStudentsWithHighestMarks() {
		Student student = orderByMarkDescending().stream().findFirst().get();
		 return students.stream().filter(s -> s.getGrade() == student.getGrade()).collect(Collectors.toList());
	}

	@Override
	public Map<Integer, List<Double>> getMarksDistributionByAge() {

		Map<Integer, List<Student>> collect = students.stream().collect(Collectors.groupingBy(s -> s.getAge()));
		Map<Integer, List<Double>> result = new HashMap<Integer, List<Double>>();
		
		collect.entrySet().stream().forEach(ent -> {
			List<Double> value = ent.getValue().stream().map(s -> s.getGrade()).collect(Collectors.toList());
			result.put(ent.getKey(), value);
		});
		
		return result;
	}

	@Override
	public Map<Gender, Double> getAverageMarkByGender() {
		
		Map<Gender, Double> result = new HashMap<Gender, Double>();
		Map<Gender, List<Student>> collect = students.stream().collect(Collectors.groupingBy(s -> s.getGender()));
		
		collect.entrySet().stream().forEach(ent -> {
			List<Student> value = ent.getValue();
			Double avg = value.stream().mapToDouble(s -> s.getGrade()).average().getAsDouble();
			result.put(ent.getKey(), avg);
		});
		return result;
	}

	@Override
	public Map<Double, Integer> getMarksDistribution() {
		Map<Double, Integer> result = new HashMap<Double, Integer>();
		Map<Double, List<Student>> collect = students.stream().collect(Collectors.groupingBy(s -> s.getGrade()));
		
		collect.entrySet().stream().forEach((ent) -> {
			result.put(ent.getKey(), ent.getValue().size());
		});
		return result;
	}

	@Override
	public String getEmailToHeader() {
		return students.stream().map(s -> s.getEmail()).collect(Collectors.joining(", "));
	}

	@Override
	public Map<Gender, Map<Integer, List<Student>>> splitStudentMarksByGenderAndThenByAge() {
		Map<Gender, Map<Integer, List<Student>>> result = new HashMap<Gender, Map<Integer,List<Student>>>();
		Map<Gender, List<Student>> splitByGender = students.stream().collect(Collectors.groupingBy(s -> s.getGender()));
		
		splitByGender.entrySet().stream().forEach(ent -> {
			Map<Integer, List<Student>> splitByAge = ent.getValue().stream().collect(Collectors.groupingBy(s -> s.getAge()));
			result.put(ent.getKey(), splitByAge);
		});
		return result;
	}
	
//	String names = persons.stream()
//			.filter(p -> p.getAge() > 18)
//			.sorted((p1,p2) -> p1.getAge() - p2.getAge())
//			.map(p -> p.getAge() + ":" + p.getName())
//			.collect(Collectors.joining(", "));

	//result "21:Kiro, 25:Ivan, 28:Yordan"
}
