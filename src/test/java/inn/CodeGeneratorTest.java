package inn;

import org.junit.Test;

import inn.CodeGenerator.CompanyType;

// import static org.junit.Assert.*;

public class CodeGeneratorTest {
	@Test public void testInn10() {
		System.out.println(CodeGenerator.getInn(CompanyType.Juridical));
		System.out.println(CodeGenerator.getKpp());
		System.out.println(CodeGenerator.getOgrn(CompanyType.Juridical));
		
		System.out.println(CodeGenerator.getInn(CompanyType.Physical));
		System.out.println(CodeGenerator.getKpp());
		System.out.println(CodeGenerator.getOgrn(CompanyType.Physical));
		
        // assertTrue("someLibraryMethod should return 'true'", true);
    }
}
