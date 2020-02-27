package com.tuvi.tuvi2019.Enum;

public class CommonEnum {

    public enum Tuvi {
        TUVI_2020("2020", 2020),
        TUVI_2019("2019", 2019),
        TUVI_TRONDOI("trondoi", 0);

        private String stringValue;
        private int intValue;

        public String getStringValue() {
            return stringValue;
        }

        public int getValue() {
            return intValue;
        }

        private Tuvi(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }
}
