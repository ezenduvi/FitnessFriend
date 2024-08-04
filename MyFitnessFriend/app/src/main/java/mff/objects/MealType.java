package mff.objects;

public enum MealType {
    BREAKFAST {
        @Override
        public String toString() {
            return "BREAKFAST";
        }
    }, LUNCH {
        @Override
        public String toString() {
            return "LUNCH";
        }
    }, DINNER {
        @Override
        public String toString() {
            return "DINNER";
        }
    }, SNACK {
        @Override
        public String toString() {
            return "SNACK";
        }
    }, WATER {
        @Override
        public String toString() {
            return "WATER";
        }
    }
}
