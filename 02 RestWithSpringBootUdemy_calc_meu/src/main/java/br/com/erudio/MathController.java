package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsuportedMathOperationException;

@RestController
public class MathController {
	
	private static final String template = "Hello, %s!";
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		if ( ! isNumeric(numberOne) || ! isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Definir um valor numerico!!!");
		}
		
		Double sum  = convertToDouble(numberOne) + convertToDouble(numberTwo);
		
		return sum;
	}

	
	@RequestMapping(value="/sub/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtract(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		if ( ! isNumeric(numberOne) || ! isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Definir um valor numerico!!!");
		}
		
		Double sub  = convertToDouble(numberOne) - convertToDouble(numberTwo);
		
		return sub;
	}

	private Double convertToDouble(String strNumber) {
		
		Double resultado = 0D; 
		
		if (strNumber != null) {

			String number = strNumber.replaceAll(",", ".");
		
			if (isNumeric(number)) {
				resultado = Double.parseDouble(number);
			}
		}

		return resultado;

	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

//	private Double convertToDouble(String strNumber) {
//	if (strNumber == null) return 0D;
//	String number = strNumber.replaceAll(",", ".");
//	
//	if (isNumeric(number)) return Double.parseDouble(number);
//	
//	return 0D;
//}
}
