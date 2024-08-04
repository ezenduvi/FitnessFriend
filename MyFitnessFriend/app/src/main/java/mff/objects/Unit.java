package mff.objects;

public enum Unit {
    CUP{
        @Override
        public String toString() {
            return "cup";
        }
    },
    GRAM {
        @Override
        public String toString() {
            return "gram";
        }
    },
    OZ{
        @Override
        public String toString() {
            return "oz";
        }
    },
    SLICES{
        @Override
        public String toString() {
            return "slices";
        }
    },
    TBSP {
        @Override
        public String toString() {
            return "tbsp";
        }
    },
    SMALL{
        @Override
        public String toString() {
        return "small";
    }
    },
    MEDIUM{
        @Override
        public String toString() {
            return "medium";
        }
    },
    LARGE{
        @Override
        public String toString() {
            return "large";
        }
    },
    PIECES{
        @Override
        public String toString() {
            return "pieces";
        }
    },

    NONE
}
