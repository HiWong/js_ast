package js_ast;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.mindlin.jsast.impl.tree.ForEachLoopTreeImpl;
import com.mindlin.jsast.tree.ForEachLoopTree;

public class ToStringTest {
	
	@Test
	public void test() {
		ForEachLoopTree loop = new ForEachLoopTreeImpl(0, 1, true, null, null, null);
		System.out.println(loop.toString());
		fail("Not yet implemented");
	}
	
}