package com.paladin.framework.utils.reflect;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.beans.BeanCopier;

public class ClassFieldUtil {
	
	static {
		
		
		
	}
	
	
	public static void main(String[] main) {

		BeanCopier copier = BeanCopier.create(A.class, A2.class, false);
		
		A a= new A();
		B b = new B();
		b.setB1("b1");
		b.setB2("b2");
		a.setA1("a1");
		a.setA2(1);
		a.setA3(b);
		
		List<String> a4 = new ArrayList<>();
		a4.add("123");
		a.setA4(a4);
		
		A2 a2 = new A2();
		copier.copy(a, a2, new org.springframework.cglib.core.Converter() {

			@Override
			public Object convert(Object arg0, Class arg1, Object arg2) {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		System.out.println(a2.getA2());
	}
	
	public static class AA{
		private String a1;
		public String getA1() {
			return a1;
		}

		public void setA1(String a1) {
			this.a1 = a1;
		}
	}
	
	public static class A extends AA{
		
		private Integer a2;
		
		private B a3;
		private List<String> a4;
	

		public Integer getA2() {
			return a2;
		}

		public void setA2(Integer a2) {
			this.a2 = a2;
		}

		public B getA3() {
			return a3;
		}

		public void setA3(B a3) {
			this.a3 = a3;
		}

		public List<String> getA4() {
			return a4;
		}

		public void setA4(List<String> a4) {
			this.a4 = a4;
		}		
	}
	
	public static class A2{
		
		private String a1;
		private int a2;
		private List<Integer> a4;
		
		private B2 a3;

		public String getA1() {
			return a1;
		}

		public void setA1(String a1) {
			this.a1 = a1;
		}



		public B2 getA3() {
			return a3;
		}

		public void setA3(B2 a3) {
			this.a3 = a3;
		}

		public int getA2() {
			return a2;
		}

		public void setA2(int a2) {
			this.a2 = a2;
		}

		public List<Integer> getA4() {
			return a4;
		}

		public void setA4(List<Integer> a4) {
			this.a4 = a4;
		}		
	}
	
	public static class B{
		private String b1;
		private String b2;
		public String getB1() {
			return b1;
		}
		public void setB1(String b1) {
			this.b1 = b1;
		}
		public String getB2() {
			return b2;
		}
		public void setB2(String b2) {
			this.b2 = b2;
		}
		
		
	}
	
	public static class B2{
		private String b1;
		private String b2;
		public String getB1() {
			return b1;
		}
		public void setB1(String b1) {
			this.b1 = b1;
		}
		public String getB2() {
			return b2;
		}
		public void setB2(String b2) {
			this.b2 = b2;
		}
		
		
	}
	
}
