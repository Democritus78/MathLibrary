public class Fraction extends NumberClass {
	private static final long serialVersionUID = 1L;
	private double numerator;
	private double denominator;

	public Fraction(double numerator, double denominator) {
		super(NumberType.FRACTION);
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public Fraction(double numerator) {
		super(NumberType.FRACTION);
		this.numerator = numerator;
		this.denominator = 1.0;
	}

	public Fraction() {
		super(NumberType.FRACTION);
	}

	public static void main(String[] args) {
		Fraction fraction = Fraction.add(new Fraction(34, 45), new Fraction(12, 34));
		System.out.print("34/45 + 12/34 = ");
		Fraction.printFraction(fraction);
		fraction = Fraction.sub(new Fraction(34, 45), new Fraction(12, 34));
		System.out.print("34/45 - 12/34 = ");
		Fraction.printFraction(fraction);
		fraction = Fraction.mul(new Fraction(34, 45), new Fraction(12, 34));
		System.out.print("34/45 * 12/34 = ");
		Fraction.printFraction(fraction);
		fraction = Fraction.div(new Fraction(34, 45), new Fraction(12, 34));
		System.out.print("34/45 / 12/34 = ");
		Fraction.printFraction(fraction);
	}

	public double getNumerator() {
		return this.numerator;
	}

	public void setNumerator(double numerator) {
		this.numerator = numerator;
	}

	public double getDenominator() {
		return denominator;
	}

	public void setDenominator(double denominator) {
		this.denominator = denominator;
	}

	@Override
	public double getValue() {
		return this.numerator / this.denominator;
	}

	public static Fraction add(Fraction first, Fraction second) {
		Fraction[] temp = Fraction.comden(first, second);
		first.setNumerator(temp[0].getNumerator() + temp[1].getNumerator());
		first.simplify();
		
		return first;
	}
	
	public static Fraction sub(Fraction first, Fraction second) {
		//System.out.println("Value of second numerator:" + second.getNumerator());
		second.setNumerator(-1.0 * second.getNumerator());
		//System.out.println("Value of second numerator:" + second.getNumerator());
		return Fraction.add(first, second);
	}
	
	public static Fraction mul(Fraction first, Fraction second) {
		first.setNumerator(first.getNumerator() * second.getNumerator());
		first.setDenominator(first.getDenominator() * second.getDenominator());
		first.simplify();
		return first;
	}
	
	public static Fraction div(Fraction first, Fraction second) {
		first.setNumerator(first.getNumerator() * second.getDenominator());
		first.setDenominator(first.getDenominator() * second.getNumerator());
		first.simplify();
		return first;
	}
	
	private void simplify() {
		double top = this.getNumerator();
		double bottom = this.getDenominator();

		if (top == bottom) {
			double result = top / top;
			this.setNumerator(result);
			this.setDenominator(result);
		}
		else {
			double common_factor = (top < bottom) ? top : bottom;

			//double limit = ;

			do {
				double numerator = this.getNumerator();
				double denominator = this.getDenominator();

				if ((numerator % common_factor == 0) && (denominator % common_factor == 0))
				{
					this.setNumerator(numerator / common_factor);
					this.setDenominator(denominator / common_factor);
				}

				
				common_factor--;
				/*
				numerator = this.getNumerator();
				denominator = this.getDenominator();
				limit = (numerator < denominator) ? numerator : denominator;
			} while(common_factor < limit);*/
			} while (common_factor > 0);
		}
	}
		/**
	 * 
	 * @param first
	 * @param second
	 * @return array of version of first and second with a common denominator
	 */
	private static Fraction[] comden(Fraction first, Fraction second) {
		double factor;
		double firstNumerator = first.getNumerator();
		double firstDenominator = first.getDenominator();
		double secondNumerator = second.getNumerator();
		double secondDenominator = second.getDenominator();
		boolean needToMultiplyDenominators = false;

		if (firstDenominator > secondDenominator) {
			if ((firstDenominator % secondDenominator) == 0) { // 0 means common factor
				factor = firstDenominator / secondDenominator;
				second.setNumerator(secondNumerator * factor);
				second.setDenominator(secondDenominator * factor);
			}
			else
				needToMultiplyDenominators = true;
		}
		else if (secondDenominator > firstDenominator) {
			if ((secondDenominator % firstDenominator) == 0) { // 0 means common factor
				factor = secondDenominator / firstDenominator;
				first.setNumerator(firstNumerator * factor);
				first.setDenominator(firstDenominator * factor);
			}
			else
				needToMultiplyDenominators = true;
		}
		else {

		}


		if (needToMultiplyDenominators) {
			first.setNumerator(firstNumerator * secondDenominator);
			first.setDenominator(firstDenominator * secondDenominator);
			second.setNumerator(secondNumerator * firstDenominator);
			second.setDenominator(secondDenominator * firstDenominator);
		}

		Fraction[] array = { first, second };

		return array;
	}
	
	public static void printFraction(Fraction fraction) {
		System.out.print((int)fraction.getNumerator() + "/" + (int)fraction.getDenominator() + "\n");
	}
}