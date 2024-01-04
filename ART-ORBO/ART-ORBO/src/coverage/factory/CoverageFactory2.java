package coverage.factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.poi.util.SystemOutLogger;
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

public class CoverageFactory2 {
	public static void main(String[] args) {
		CoverageFactory2 factory = new CoverageFactory2();
		factory.setClassName("tested.coverage.TestForCoverage");
		System.out.println(factory.execute(12));

	}

	private String ClassName;

	private String[] methodsArr = null;

	public CoverageFactory2() {
	}

	public CoverageFactory2(String ClassName, String[] methodsArr) {
		this.ClassName = ClassName;
		this.methodsArr = methodsArr;
	}

	public double execute(double a) {
		final String targetName = ClassName;
		
		final IRuntime runtime = new LoggerRuntime();
		final Instrumenter instr = new Instrumenter(runtime);
		byte[] instrumented = null;
		try {
			instrumented = instr.instrument(getTargetClass(targetName), targetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final RuntimeData data = new RuntimeData();
		try {
			runtime.startup(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// In this tutorial we use a special class loader to directly load the
		// instrumented class definition from a byte[] instances.
		final MemoryClassLoader memoryClassLoader = new MemoryClassLoader();
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
		try {
			// 改进
			targetClass.getMethod("wrong", double.class).invoke(targetInstance, a);
			//String[] b=new String[]{"",""};
			//targetClass.getMethod("main",String[].class).invoke(targetInstance,(Object)b);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		final ExecutionDataStore executionData = new ExecutionDataStore();
		final SessionInfoStore sessionInfos = new SessionInfoStore();
		data.collect(executionData, sessionInfos, false);
		runtime.shutdown();
		final CoverageBuilder coverageBuilder = new CoverageBuilder();
		final Analyzer analyzer = new Analyzer(executionData, coverageBuilder);
		try {
			analyzer.analyzeClass(getTargetClass(targetName), targetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Let's dump some metrics and line coverage information:
		double codeCoverage = 0;
		for (final IClassCoverage cc : coverageBuilder.getClasses()) {
			// System.out.printf("Coverage of class %s%n", cc.getName());
			// printCounter("instructions", cc.getInstructionCounter());
			// printCounter("branches:", cc.getBranchCounter());
			codeCoverage = printCounter("lines", cc.getLineCounter());
			// printCounter("methods", cc.getMethodCounter());
			// printCounter("complexity", cc.getComplexityCounter());
		}
		return codeCoverage;
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
		//System.out.println(total+" miss:"+missed);
		return (total - missed) / (double) total;
	}

	public void setClassName(String name) {
		this.ClassName = name;
	}
}
