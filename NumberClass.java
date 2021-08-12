public abstract class NumberClass {
    private double value;
    private NumberType type;

    NumberClass(double value, NumberType type) {
        this.value = value;
        this.type = type;
    }

    NumberClass(double value) {
        this.value = value;
        this.type = NumberType.DOUBLE;
    }

    NumberClass(NumberType type) {
        this.type = type;
    }

    /**
     * returns decimal form of subclass
     */
	public double getValue() {
        return this.value;
	}

    public NumberType getType() {
        return this.type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setType(NumberType type) {
        this.type = type;
    }
}
