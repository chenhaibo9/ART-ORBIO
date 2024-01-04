package datastructure.failurepattern.impl;

import java.util.Arrays;

import Fault_10d.CalGCD;
import Fault_11d.Select;
import Fault_12d.Tcas;
import Fault_5d.CalDay;
import Fault_6d.Complex;
import Fault_6d.Triangle;
import Fault_8d.Line;
import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;
import testPrograms.Fault_10d.NearestDistance;
import testPrograms.Fault_8d.TwoLinesPos;
import util.TestProgram;

public class RealityFailPattern extends FailurePattern {

	String projectName;

	public RealityFailPattern(String projectName) {
		super();
		this.projectName = projectName;
	}

	@Override
	public void genFailurePattern() {
		// we don't need to generate failure pattern in reality projects
	}

	@Override
	public boolean isCorrect(NPoint p) {
		double[] pxn = p.getXn();
		boolean result = false;
		String message = "the test case p's dimension is not accordingly";
		switch (projectName) {
		case "airy":
			if (pxn.length == 1) {
				result = TestProgram.test_airy(pxn[0]);
			} else {
				System.out.println(message);
			}
			break;
		case "bessj":
			// 2 dimension
			if (pxn.length == 2) {
				result = TestProgram.test_bessj(pxn[0], pxn[1]);
			} else {
				System.out.println(message);
			}
			break;
		case "bessj0":
			// 1 dimension
			if (pxn.length == 1) {
				result = TestProgram.test_bessj0(pxn[0]);
			} else {
				System.out.println(message);
			}
			break;
		case "cel":
			// 4 dimension
			if (pxn.length == 4) {
				double a = pxn[0];
				double b = pxn[1];
				double c = pxn[2];
				double d = pxn[3];
				result = TestProgram.test_cel(a, b, c, d);
			} else {
				System.out.println(message);
			}
			break;
		case "el2":
			// 4
			if (pxn.length == 4) {
				double a = pxn[0];
				double b = pxn[1];
				double c = pxn[2];
				double d = pxn[3];
				result = TestProgram.test_el2(a, b, c, d);
			}
			break;
		case "erfcc":
			// 1
			if (pxn.length == 1) {
				result = TestProgram.test_erfcc(pxn[0]);
			} else {
				System.out.println(message);
			}
			break;
		case "gammq":
			// 2
			if (pxn.length == 2) {
				result = TestProgram.test_gammq(pxn[0], pxn[1]);
			} else {
				System.out.println(message);
			}
			break;
		case "golden":
			// 3
			if (pxn.length == 3) {
				result = TestProgram.test_golden(pxn[0], pxn[1], pxn[2]);
			} else {
				System.out.println(message);
			}
			break;
		case "plgndr":
			// 3
			if (pxn.length == 3) {
				result = TestProgram.test_plgndr(pxn[0], pxn[1], pxn[2]);
			} else {
				System.out.println(message);
			}
			break;
		case "probks":
			// 1
			if (pxn.length == 1) {
				result = TestProgram.test_probks(pxn[0]);
			} else {
				System.out.println(message);
			}
			break;
		case "sncndn":
			// 2
			if (pxn.length == 2) {
				result = TestProgram.test_sncndn(pxn[0], pxn[1]);
			} else {
				System.out.println(message);
			}
			break;
		case "tanh":
			if (pxn.length == 1) {
				result = TestProgram.test_tanh(pxn[0]);
			} else {
				System.out.println(message);
			}
			break;
case "CalDay":
			
			if (pxn.length == 5) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];		
				
				result = new CalDay().isCorrect(a, b, c, d, e);
				//System.out.println(result);
				
			} else {
				System.out.println(message);
			}
			break;
			
		case "Triangle":
			
			if (pxn.length == 6) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];
				int f = (int)pxn[5];
				
					result = new Triangle().isCorrect(a, b, c, d, e, f);
					//System.out.println(result);
				
			} else {
				System.out.println(message);
			}
			break;
		case "Complex":
			
			if (pxn.length == 6) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];
				int f = (int)pxn[5];
				
					result = new Complex().isCorrect(a, b, c, d, e, f);
					//System.out.println(result);
				
			} else {
				System.out.println(message);
			}
			break;
			
		case "Line":
			
			if (pxn.length == 8) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];
				int f = (int)pxn[5];
				int g = (int)pxn[6];
				int h = (int)pxn[7];
				
					result = new Line().isCorrect(a, b, c, d, e, f, g, h);
					//System.out.println(result);
			} else {
				System.out.println(message);
			}
			break;
			
case "TwoLinesPos":
			
			if (pxn.length == 8) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];
				int f = (int)pxn[5];
				int g = (int)pxn[6];
				int h = (int)pxn[7];
				
					result = new TwoLinesPos().isCorrect(a, b, c, d, e, f, g, h);
					//System.out.println(result);
			} else {
				System.out.println(message);
			}
			break;
			
		case "CalGCD":
			
			if (pxn.length == 10) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];
				int f = (int)pxn[5];
				int g = (int)pxn[6];
				int h = (int)pxn[7];
				int i = (int)pxn[8];
				int j = (int)pxn[9];
				
					result = new CalGCD().isCorrect(a, b, c, d, e, f, g, h, i, j);
					//System.out.println(result);
			} else {
				System.out.println(message);
			}
			break;
			
case "NearestDistance":
			
			if (pxn.length == 10) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];
				int f = (int)pxn[5];
				int g = (int)pxn[6];
				int h = (int)pxn[7];
				int i = (int)pxn[8];
				int j = (int)pxn[9];
				
					result = new NearestDistance().isCorrect(a, b, c, d, e, f, g, h, i, j);
					//System.out.println(result);
			} else {
				System.out.println(message);
			}
			break;
			
		case "Tcas":
			
				if (pxn.length == 12) {
					int a = (int)pxn[0];
					int b = (int)pxn[1];
					int c = (int)pxn[2];
					int d = (int)pxn[3];
					int e = (int)pxn[4];
					int f = (int)pxn[5];
					int g = (int)pxn[6];
					int h = (int)pxn[7];
					int i = (int)pxn[8];
					int j = (int)pxn[9];
					int k = (int)pxn[10];
					int l = (int)pxn[11];
					//Tcas ts = new Tcas();
					//result = new Tcas().isCorrect(p);
					try {
						result = new Tcas().isCorrect(a, b, c, d, e, f, g, h, i, j, k, l);
						//System.out.println(result);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					System.out.println(message);
				}
				break;
		case "Select":
			
			if (pxn.length == 11) {
				int a = (int)pxn[0];
				int b = (int)pxn[1];
				int c = (int)pxn[2];
				int d = (int)pxn[3];
				int e = (int)pxn[4];
				int f = (int)pxn[5];
				int g = (int)pxn[6];
				int h = (int)pxn[7];
				int i = (int)pxn[8];
				int j = (int)pxn[9];
				int k = (int)pxn[10];
				//Tcas ts = new Tcas();
				//result = new Tcas().isCorrect(p);
				try {
					result = new Select().isCorrect(a, b, c, d, e, f, g, h, i, j, k);
					//System.out.println(result);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				System.out.println(message);
			}
			break;
		default:
			System.out.println("error program name!");
			break;
		}
		return result;
	}

	@Override
	public void showFailurePattern() {
		System.out.println( "RealityFailPattern [projectName=" + projectName + ", dimension=" + dimension + ", min="
				+ Arrays.toString(min) + ", max=" + Arrays.toString(max) + ", fail_rate=" + fail_rate + ", random="
				+ random + "]");
	}


}
