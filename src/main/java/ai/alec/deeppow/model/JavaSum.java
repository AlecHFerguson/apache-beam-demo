package ai.alec.deeppow.model;

public class JavaSum {
    private final double totalACPower;
    private final String dateTime;

    private JavaSum(Builder builder) {
        this.totalACPower = builder.totalACPower;
        this.dateTime = builder.dateTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private double totalACPower;
        private String dateTime;

        public Builder setTotalACPower(double totalACPower) {
            this.totalACPower = totalACPower;
            return this;
        }

        public Builder setDateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public JavaSum build() {
            return new JavaSum(this);
        }
    }
}
