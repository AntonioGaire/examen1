package es.iesvegademijas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import es.iesvegademijas.streams.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

class EmpleadosStreamTests {

	@Test
	void testSkeletonDepartamento() {
	
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
		
			
			//TODO STREAMS
			
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	

	@Test
	void testSkeletonEmpleado() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listProd = empHome.findAll();		
						
			//TODO STREAMS
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	@Test
	void testAllDepartamento() {
	
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllEmpleado() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();		
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 1. Lista el código de los departamentos de los empleados, 
	 * eliminando los códigos que aparecen repetidos.
	 */
	@Test
	void test1() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var listCodigoDepartamento = listEmp.stream()
					.map(Empleado :: getDepartamento)
					.filter(d -> d != null)
					.map(Departamento :: getCodigo)
					.distinct()
					.collect(toList());
			
			listCodigoDepartamento.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 2. Lista el nombre y apellidos de los empleados en una única columna, convirtiendo todos los caracteres en mayúscula 
	 * 
	 */
	@Test
	void test2() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var listanombre = listEmp.stream()
					.map(e -> e.getNombre().toUpperCase() + " " + e.getApellido1().toUpperCase() +e.getApellido2())
					.collect(toList());
			
			listanombre.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}

	/**
	 * 3. Lista el código de los empleados junto al nif, pero el nif deberá aparecer en dos columnas, 
	 * una mostrará únicamente los dígitos del nif y la otra la letra.
	 */
	
	@Test
	void test3() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var listanombre = listEmp.stream()
					.map(e -> e.getCodigo() + " " + e.getNif().substring(0,8) + "\t" + e.getNif().charAt(8))
					.collect(toList());
			
			
			listanombre.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 4. Lista el nombre de cada departamento y el valor del presupuesto actual del que dispone. 
	 * Para calcular este dato tendrá que restar al valor del presupuesto inicial (columna presupuesto) los gastos que se han generado (columna gastos).
	 *  Tenga en cuenta que en algunos casos pueden existir valores negativos.
	 */
	@Test
	void test4() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			
			var listDepPres = listDep.stream()
					.map(d -> d.getNombre() + " con presupuesto: " + (d.getPresupuesto()-d.getGastos()))
					.collect(toList());
			
			listDepPres.forEach(System.out::println);
			 
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 5. Lista el nombre de los departamentos y el valor del presupuesto actual ordenado de forma ascendente.
	 */
	@Test
	void test5() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		//TODO
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var listDepPres = listDep.stream()
					.sorted(comparing(Departamento :: getPresupuesto))
					.map(d -> d.getNombre())
					.collect(toList());			
			listDepPres.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 6. Devuelve una lista con el nombre y el presupuesto, de los 3 departamentos que tienen mayor presupuesto
	 */
	@Test
	void test6() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var listDepPres = listDep.stream()
					.sorted(comparing(Departamento :: getPresupuesto).reversed())
					.limit(3)
					.collect(toList());
			
			listDepPres.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 7. Devuelve una lista con el nombre de los departamentos y el presupesto, de aquellos que tienen un presupuesto entre 100000 y 200000 euros
	 */
	@Test
	void test7() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var listDepPres = listDep.stream()
					.filter(d -> d.getPresupuesto()>=100000 && d.getPresupuesto()<=200000)
					.collect(toList());
			
			listDepPres.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 8. Devuelve una lista con 5 filas a partir de la tercera fila de empleado ordenado por código de empleado. La tercera fila se debe incluir en la respuesta.
	 */
	@Test
	void test8() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var listEmpTrunc = listEmp.stream()
					.sorted(comparing(Empleado :: getCodigo))
					.skip(2)
					.limit(5)
					.collect(toList());
			
			listEmpTrunc.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 9. Devuelve una lista con el nombre de los departamentos y el gasto, de aquellos que tienen menos de 5000 euros de gastos.
	 * Ordenada de mayor a menor gasto.
	 */
	@Test
	void test9() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var listDepPres = listDep.stream()
					.filter(d -> d.getGastos()<5000)
					.sorted(comparing(Departamento :: getGastos).reversed())
					.map(d -> d.getNombre() + " con gastos: " + d.getGastos())
					.collect(toList());			
			listDepPres.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 10. Lista todos los datos de los empleados cuyo segundo apellido sea Díaz o Moreno
	 */
	@Test
	void test10() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var listEmpAp2 = listEmp.stream()
					.filter(e -> e.getApellido2()!=null)
					.filter(e -> e.getApellido2().equalsIgnoreCase("Díaz") || e.getApellido2().equalsIgnoreCase("Moreno"))
					.collect(toList());
			
			listEmpAp2.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 11. Lista los nombres, apellidos y nif de los empleados que trabajan en los departamentos 2, 4 o 5
	 */
	@Test
	void test11() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var listEmpDep = listEmp.stream()
					.filter(e -> e.getDepartamento()!=null)
					.filter(e-> e.getDepartamento().getCodigo()==2 ||
							e.getDepartamento().getCodigo()==5 ||
							e.getDepartamento().getCodigo()==4)
					.map(e -> e.getNombre() + " " + e.getApellido1() +" "+ e.getApellido2() +" "+ e.getNif())
					.collect(toList());
			
			listEmpDep.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	
	/**
	 * 12. Devuelve el nombre del departamento donde trabaja el empleado que tiene el nif 38382980M
	 */
	@Test
	void test12() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var dep = listEmp.stream()
					.filter(e -> e.getNif().equals("38382980M"))
					.map(e -> e.getDepartamento().getNombre())
					.collect(toList());
					
			
			System.out.println(dep);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 13. Devuelve una lista con el nombre de los empleados que tienen los departamentos 
	 * que no tienen un presupuesto entre 100000 y 200000 euros.
	 */
	@Test
	void test13() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var listEmpTrunc = listEmp.stream()
					.filter(e -> e.getDepartamento()!=null)
					.filter(e -> e.getDepartamento().getPresupuesto()>=100000 && e.getDepartamento().getPresupuesto()<=200000 )
					.map(e -> e.getNombre() + " " + e.getApellido1() +" "+ e.getApellido2())
					.collect(toList());
			
			listEmpTrunc.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 14. Calcula el valor mínimo del presupuesto de todos los departamentos.
	 */
	@Test
	void test14() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var listDepPres = listDep.stream()
					.sorted(comparing(Departamento :: getPresupuesto))
					.limit(1)
					.map(Departamento :: getPresupuesto)
					.collect(toList());
			
			listDepPres.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 15. Calcula el número de empleados que hay en cada departamento. 
	 * Tienes que devolver dos columnas, una con el nombre del departamento 
	 * y otra con el número de empleados que tiene asignados.
	 */
	@Test
	void test15() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var nemp = listDep.stream()
					.map((d)->{
						return d.getNombre() + ": "+ d.getEmpleados().stream().count();
					})
					.collect(toList());
			
			nemp.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 16. Calcula el número total de empleados que trabajan en cada unos de los departamentos que tienen un presupuesto mayor a 200000 euros.
	 */
	@Test
	void test16() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var nemp = listDep.stream()
					.filter(d-> d.getPresupuesto()>200000)
					.map((d)->{
						return d.getNombre() + ": "+ d.getEmpleados().stream().count();
					})
					.collect(toList());
			
			nemp.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 17. Calcula el nombre de los departamentos que tienen más de 2 empleados. 
	 * El resultado debe tener dos columnas, una con el nombre del departamento y
	 *  otra con el número de empleados que tiene asignados
	 */
	@Test
	void test17() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var nemp = listDep.stream()
					.filter((d)->{
						return (d.getEmpleados().stream().count())>=2;})
					.map((d)->{
						return d.getNombre() + ": "+ d.getEmpleados().stream().count();
					})
					.collect(toList());
			
			nemp.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/** 18. Lista todos los nombres de departamentos junto con los nombres y apellidos de los empleados. 
	 * 
	 */
	@Test
	void test18() {
		
		//TODO
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var depYnom = listDep.stream()
					.map(d -> d.getNombre())
					.collect(toList());
			
			depYnom.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 19. Devuelve la media de empleados que trabajan en los departamentos
	 */
	@Test
	void test19() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var nemp = listDep.stream()
					.map( d-> d.getEmpleados().stream().count())
					.reduce((a,b) -> a+b)
					.orElse(0l);
			var ndep = listDep.stream()
					.count();
			
			System.out.println((double)((double)nemp / (double)ndep));
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 20. Calcula el presupuesto máximo, mínimo  y número total de departamentos con un solo stream.
	 */
	@Test
	void test20() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
}
