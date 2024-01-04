package coverage.factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.ICounter;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.instr.Instrumenter;
import org.jacoco.core.runtime.IRuntime;
import org.jacoco.core.runtime.LoggerRuntime;
import org.jacoco.core.runtime.RuntimeData;

import coverage.classloader.MemoryClassLoader;

public class CoverageFactory {
	public static void main(String[] args) {
		CoverageFactory factory = new CoverageFactory();
		factory.setClassName("tested.bessj0");
		System.out.println(factory.execute(12));
		// tested.bessj0 bessj = new tested.bessj0();

	}

	String name;

	public double execute(double a) {
		// �õ��������������
		final String targetName = name;
		// For instrumentation and runtime we need a IRuntime instance to
		// collect execution data:
		// jacoco ����ṩ
		final IRuntime runtime = new LoggerRuntime();
		// The Instrumenter creates a modified version of our test target class
		// that contains additional probes for execution data recording:
		final Instrumenter instr = new Instrumenter(runtime);
		byte[] instrumented = null;
		try {
			// get the bytes that has been instructed

			instrumented = instr.instrument(getTargetClass(targetName), targetName);
			// for (byte b : instrumented) {
			// System.out.print(b);
			// }
			// System.out.println();
			// File f=new
			// File("F:/Users/xijiaxiang/workspace/ST_CodeCoverageOfART_TP/bin/tested/bessj0_instr.class");
			// if(!f.exists()){
			// f.createNewFile();
			// FileOutputStream foutput=new FileOutputStream(f);
			// foutput.write(instrumented);
			// }
			// FileInputStream finput=new FileInputStream(f);
			// int aa;
			// int count=0;
			// instrumented=new byte[(int) f.length()];
			// while((aa=finput.read())!=-1){
			// instrumented[count]=()aa;
			// }
			// FileInputStream finput=new FileInputStream(f);
			// int aa;
			// while((aa=finput.read())!=-1){
			// System.out.print(aa);
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Now we're ready to run our instrumented class and need to startup the
		// runtime first:
		final RuntimeData data = new RuntimeData();
		try {
			runtime.startup(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// In this tutorial we use a special class loader to directly load the
		// instrumented class definition from a byte[] instances.
		final MemoryClassLoader memoryClassLoader = new MemoryClassLoader();
		// name and bytes to be a key and value
		memoryClassLoader.addDefinition(targetName, instrumented);
		Class<?> targetClass = null;
		try {
			targetClass = memoryClassLoader.loadClass(targetName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Here we execute our test target class through its Runnable interface:
		// final Runnable targetInstance = (Runnable) targetClass.newInstance();
		// targetInstance.run();
		Object targetInstance = null;
		try {
			targetInstance = targetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		// targetClass.getMethod("isPrimer",Boolean.class);
		
		try {
			targetClass.getMethod("wrong", double.class).invoke(targetInstance, a);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		// classs.isPrime(3);
		// At the end of test execution we collect execution data and shutdown
		// the runtime:
		final ExecutionDataStore executionData = new ExecutionDataStore();
		final SessionInfoStore sessionInfos = new SessionInfoStore();
		data.collect(executionData, sessionInfos, false);
		runtime.shutdown();
		// Together with the original class definition we can calculate coverage
		// information:
		final CoverageBuilder coverageBuilder = new CoverageBuilder();
		final Analyzer analyzer = new Analyzer(executionData, coverageBuilder);
		try {
			analyzer.analyzeClass(getTargetClass(targetName), targetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Let's dump some metrics and line coverage information:
		double codeCoverage = 0;
		for (final IClassCoverage cc : coverageBuilder.getClasses()) {
			// System.out.printf("Coverage of class %s%n", cc.getName());
			//
			// printCounter("instructions", cc.getInstructionCounter());
			// printCounter("branches:", cc.getBranchCounter());
			codeCoverage = printCounter("lines", cc.getLineCounter());
			// printCounter("methods", cc.getMethodCounter());
			// printCounter("complexity", cc.getComplexityCounter());
		}
		// System.out.println(10-9*codeCoverage);
		return 10.0 - 9 * codeCoverage;
	}

	private InputStream getTargetClass(final String name) {
		final String resource = '/' + name.replace('.', '/') + ".class";
		return getClass().getResourceAsStream(resource);
	}

	private double printCounter(final String unit, final ICounter counter) {
		final Integer missed = Integer.valueOf(counter.getMissedCount());
		final Integer total = Integer.valueOf(counter.getTotalCount());
		// System.out.printf("%s of %s %s missed%n", missed, total, unit);
		// System.out.println((total-missed)/(double)total);
		return (total - missed) / (double) total;
	}

	public void setClassName(String name) {
		this.name = name;
	}
}
