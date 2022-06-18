package ai.alec.deeppow.model;

import static java.lang.Double.parseDouble;

public class JavaReading {
    private final double acPower;
    private final double dailyYield;
    private final String dateTime;
    private final double dcPower;
    private final String invalidReason;
    private final String plantId;
    private final String rawInput;
    private final String sourceKey;
    private final double totalYield;
    private final boolean valid;

    public static JavaReading parseFromString(String rawRow) {
        final Builder builder = new Builder(rawRow);
        String[] splitRow = rawRow.split(",");

        try {
            return builder
                    .setAcPower(parseDouble(splitRow[4]))
                    .setDailyYield(parseDouble(splitRow[5]))
                    .setDateTime(splitRow[0])
                    .setDcPower(parseDouble(splitRow[3]))
                    .setPlantId(splitRow[1])
                    .setSourceKey(splitRow[2])
                    .setTotalYield(parseDouble(splitRow[6]))
                    .setValid(true)
                    .build();
        } catch (IndexOutOfBoundsException e) {
            return builder
                    .setInvalidReason(e.getMessage())
                    .setValid(false)
                    .build();
        } catch (NumberFormatException e) {
            return builder
                    .setInvalidReason(e.getMessage())
                    .setValid(false)
                    .build();
        }
    }

    private JavaReading(Builder builder) {
        this.acPower = builder.acPower;
        this.dailyYield = builder.dailyYield;
        this.dateTime = builder.dateTime;
        this.dcPower = builder.dcPower;
        this.invalidReason = builder.invalidReason;
        this.plantId = builder.plantId;
        this.rawInput = builder.rawInput;
        this.sourceKey = builder.sourceKey;
        this.totalYield = builder.totalYield;
        this.valid = builder.valid;
    }

    public double getAcPower() {
        return acPower;
    }

    public double getDailyYield() {
        return dailyYield;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public double getDcPower() {
        return dcPower;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public String getRawInput() {
        return rawInput;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public double getTotalYield() {
        return totalYield;
    }

    public boolean isValid() {
        return valid;
    }

    public static class Builder {
        private double acPower = Double.NaN;
        private double dailyYield = Double.NaN;
        private String dateTime = "";
        private double dcPower = Double.NaN;
        private String invalidReason = null;
        private String plantId = null;
        private String rawInput;
        private String sourceKey = null;
        private double totalYield = Double.NaN;
        private boolean valid = true;

        public Builder(String rawInput) {
            this.rawInput = rawInput;
        }

        public Builder setAcPower(double acPower) {
            this.acPower = acPower;
            return this;
        }

        public Builder setDailyYield(double dailyYield) {
            this.dailyYield = dailyYield;
            return this;
        }

        public Builder setDateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setDcPower(double dcPower) {
            this.dcPower = dcPower;
            return this;
        }

        public Builder setInvalidReason(String invalidReason) {
            this.invalidReason = invalidReason;
            return this;
        }

        public Builder setPlantId(String plantId) {
            this.plantId = plantId;
            return this;
        }

        public Builder setSourceKey(String sourceKey) {
            this.sourceKey = sourceKey;
            return this;
        }

        public Builder setTotalYield(double totalYield) {
            this.totalYield = totalYield;
            return this;
        }

        public Builder setValid(boolean valid) {
            this.valid = valid;
            return this;
        }

        public JavaReading build() {
            return new JavaReading(this);
        }
    }
}
