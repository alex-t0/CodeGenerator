package inn;

import java.math.BigInteger;
import java.security.SecureRandom;

public class CodeGenerator {
	public enum CompanyType {
		Juridical, Physical
	}
	
	public static class ArrayWorker {
		private static void fillRandom(int[] array, int rightSkip){
			SecureRandom random = new SecureRandom();
			
			for (int i = 0; i < array.length - rightSkip; i++){
				array[i] = random.nextInt(10);  
			}
		}
		
		private static String convertArrayToString(int[] array){
			String result = "";
			
			for (int i = 0; i < array.length; i++) 
				result += new Integer(array[i]).toString();
			
			return result;
		}
	}
	
	private static int[] innControls12_1 = {3,7,2,4,10,3,5,9,4,6,8};
	private static int[] innControls12_2 = {7,2,4,10,3,5,9,4,6,8};
	private static int[] innControls10 = {2,4,10,3,5,9,4,6,8};
	
	public static String getInn(CompanyType type){
		if (type == CompanyType.Juridical)
			return getInn10();
		return getInn12();
	}
	
	public static String getOgrn(CompanyType type){
		int[] ogrn;
		int divisor;
		if (type == CompanyType.Juridical) {
			ogrn = new int[13];
			divisor = 11;
		}
		else { 
			ogrn = new int[15];
			divisor = 13;
		}
		
		ArrayWorker.fillRandom(ogrn, 1);
		
		if (type == CompanyType.Juridical) {
			SecureRandom sr = new SecureRandom();
			int rnd = sr.nextInt(2);
			ogrn[0] = rnd == 0 ? 1 : 5;
		}
		else { 
			ogrn[0] = 3;
		}
		
		BigInteger bi = new BigInteger(ArrayWorker.convertArrayToString(ogrn)).divide(BigInteger.valueOf(10));
		int rem = bi.mod(BigInteger.valueOf(divisor)).intValue() % 10;
		ogrn[ogrn.length - 1] = rem;
		
		return ArrayWorker.convertArrayToString(ogrn);
	}
	
	public static String getKpp(){
		int[] kpp = new int[9];
		ArrayWorker.fillRandom(kpp, 0);
		return ArrayWorker.convertArrayToString(kpp);
	}
	
	private static String getInn12(){
		int[] result = new int[12];
		ArrayWorker.fillRandom(result, 2);
		result[10] = calculateControlNumber(result, innControls12_2);
		result[11] = calculateControlNumber(result, innControls12_1);
		return ArrayWorker.convertArrayToString(result);
	}
	
	private static String getInn10(){
		int[] result = new int[10];
		ArrayWorker.fillRandom(result, 1);
		result[9] = calculateControlNumber(result, innControls10);
		return ArrayWorker.convertArrayToString(result);
	}
		
	private static int calculateControlNumber(int[] array, int[] controlArray) {
		int sum = 0;
		
		for (int i = 0; i < controlArray.length; i++)
			sum += array[i] * controlArray[i];
		
		int rem = sum % 11;
		if (rem == 10) rem = 0;
		
		return rem;
	}
}
