package com.ouieat.requests.handler;

public interface FunctionalInterfaces {
    @FunctionalInterface
    public interface Function2<One, Two, Three> {
        public Three apply(One one, Two two);
    }

    @FunctionalInterface
    public interface Function3<One, Two, Three, Four> {
        public Four apply(One one, Two two, Three three);
    }

    @FunctionalInterface
    public interface Function4<One, Two, Three, Four, Five> {
        public Five apply(One one, Two two, Three three, Four four);
    }

    @FunctionalInterface
    public interface Function5<One, Two, Three, Four, Five, Six> {
        public Six apply(One one, Two two, Three three, Four four, Five five);
    }

    @FunctionalInterface
    public interface Function6<One, Two, Three, Four, Five, Six, Seven> {
        public Seven apply(
            One one,
            Two two,
            Three three,
            Four four,
            Five five,
            Six six
        );
    }
}
