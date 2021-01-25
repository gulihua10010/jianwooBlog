package cn.jianwoo.blog.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-25 16:25
 */
public class JwBuilder<T> {
    private final Supplier<T> instance;
    private final List<Consumer<T>> modifiers = new ArrayList<>();

    public JwBuilder(Supplier<T> instance) {
        this.instance = instance;
    }

    public static <T> JwBuilder<T> of(Supplier<T> instance) {
        return new JwBuilder<>(instance);
    }

    public <U> JwBuilder<T> with(BiConsumer<T, U> biConsumer, U value) {
        Consumer<T> consumer = instance -> biConsumer.accept(instance, value);
        modifiers.add(consumer);
        return this;
    }

    public T build() {
        T t = instance.get();
        modifiers.forEach(modifier -> {
            modifier.accept(t);
        });
        modifiers.clear();
        return t;

    }
}
