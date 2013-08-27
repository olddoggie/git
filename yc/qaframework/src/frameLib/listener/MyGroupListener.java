package frameLib.listener;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

/**
 * @author jyu
 * Use this listener to run group by the nature order of its name.
 * For example, @Test(groups = { "tg10_40" }),@Test(groups = { "tg10_20" })
 * tg10_20 will run right before tg10_40. 
 * This interceptor will override the priority (@Test(priority = 15)). So priority setting will not work
 * 
 */


public class MyGroupListener implements IMethodInterceptor {
	//@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods,
			ITestContext context) {
		Comparator<IMethodInstance> comparator = new Comparator<IMethodInstance>() {

			private String getGroup(IMethodInstance mi) {
				if (mi.getMethod().getGroups().length > 0) {
					return mi.getMethod().getGroups()[0];
				} else {
					return "default";
				}

			}

			//@Override
			public int compare(IMethodInstance m1, IMethodInstance m2) {
				return getGroup(m1).compareTo(getGroup(m2));
			}

		};
		IMethodInstance[] array = methods.toArray(new IMethodInstance[methods
				.size()]);
		Arrays.sort(array, comparator);

		return Arrays.asList(array);
	}
}

