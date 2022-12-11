package com.ouieat.requests.handler;

public interface FunctionalInterfaces {
    @FunctionalInterface
    interface Function2<One, Two, Three> {
        Three apply(One one, Two two);
    }

    @FunctionalInterface
    interface Function3<One, Two, Three, Four> {
        Four apply(One one, Two two, Three three);
    }

    @FunctionalInterface
    interface Function4<One, Two, Three, Four, Five> {
        Five apply(One one, Two two, Three three, Four four);
    }

    @FunctionalInterface
    interface Function5<One, Two, Three, Four, Five, Six> {
        Six apply(One one, Two two, Three three, Four four, Five five);
    }

    @FunctionalInterface
    interface Function6<One, Two, Three, Four, Five, Six, Seven> {
        Seven apply(
            One one,
            Two two,
            Three three,
            Four four,
            Five five,
            Six six
        );
    }
}
