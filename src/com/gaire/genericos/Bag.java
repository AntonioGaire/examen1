package com.gaire.genericos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Bag <E>{
	
	private List<E> myBag;
	
	Bag(){
		myBag = new ArrayList<E>();
	}
	
	public boolean add(E e) {
		return myBag.add(e);
	}
	
	public boolean add(E e, int n) {
		
		boolean chk=false;
		
		for(int i=0; i<n; i++) {
			chk=myBag.add(e);
		}
		
		return chk;
	}
	
	public long getCount(E e) {
		
		long ne=0;
		if(myBag.contains(e)) {
			ne=myBag.stream()
			.filter(b -> b.equals(e))
			.count();
		}
		
		return ne;
	}
	
	public boolean remove(E e) {
		boolean chk=false;
		
			while(myBag.contains(e)) {
				chk=true;
				myBag.remove(e);
			}
			
		return chk;
	}
	
	public boolean remove (E e, int n) {
		boolean chk=false;
		
			for(int i=0; i<n; i++) {
				myBag.remove(e);
				chk=true;
			}
				
		return chk;
	}
	
	public int size() {
		
		return myBag.size();
		
	}
	
	public Set<E> uniqueSet(){
		Set<E> mySet = new HashSet<E>();
		
		for(int i=0; i< myBag.size(); i++) {
			if(!mySet.contains(myBag.get(i))){
				mySet.add(myBag.get(i));
			}
		}
		return mySet;
	}
}
