package io.renren.common.example;

import java.util.HashMap;
import java.util.Map;

import io.renren.common.example.generator.AbstractPredicateGenerator;
import io.renren.common.example.generator.EqPredicateGenerator;
import io.renren.common.example.generator.GePredicateGenerator;
import io.renren.common.example.generator.GtPredicateGenerator;
import io.renren.common.example.generator.InPredicateGenerator;
import io.renren.common.example.generator.IsEmptyPredicateGenerator;
import io.renren.common.example.generator.IsNotEmptyPredicateGenerator;
import io.renren.common.example.generator.IsNotNullPredicateGenerator;
import io.renren.common.example.generator.IsNullPredicateGenerator;
import io.renren.common.example.generator.LePredicateGenerator;
import io.renren.common.example.generator.LikePredicateGenerator;
import io.renren.common.example.generator.LtPredicateGenerator;
import io.renren.common.example.generator.NePredicateGenerator;
import io.renren.common.example.generator.NotLikePredicateGenerator;
import io.renren.common.example.generator.NotinPredicateGenerator;

public final class PredicateGeneratorFactory {
    private static final Map<String, AbstractPredicateGenerator> PREDICATE_GENERATOR_MAP = new HashMap<String, AbstractPredicateGenerator>(0);

    private PredicateGeneratorFactory() {
    }

    static {
        PREDICATE_GENERATOR_MAP.put("equals", new EqPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("eq", PREDICATE_GENERATOR_MAP.get("equals"));
        PREDICATE_GENERATOR_MAP.put("ge", new GePredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("gt", new GtPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("lt", new LtPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("le", new LePredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("ne", new NePredicateGenerator());

        PREDICATE_GENERATOR_MAP.put("notlike", new NotLikePredicateGenerator("%", "%"));

        PREDICATE_GENERATOR_MAP.put("like", new LikePredicateGenerator("%", "%"));
        PREDICATE_GENERATOR_MAP.put("like:", new LikePredicateGenerator(null, "%"));
        PREDICATE_GENERATOR_MAP.put("l:", PREDICATE_GENERATOR_MAP.get("like:"));
        PREDICATE_GENERATOR_MAP.put(":like", new LikePredicateGenerator("%", null));
        PREDICATE_GENERATOR_MAP.put(":l", PREDICATE_GENERATOR_MAP.get(":like"));
        PREDICATE_GENERATOR_MAP.put(":like:", new LikePredicateGenerator("%", "%"));
        PREDICATE_GENERATOR_MAP.put(":l:", PREDICATE_GENERATOR_MAP.get(":like:"));

        PREDICATE_GENERATOR_MAP.put("isnull", new IsNullPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("isnotnull", new IsNotNullPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("isempty", new IsEmptyPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("isnotempty", new IsNotEmptyPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("in", new InPredicateGenerator());
        PREDICATE_GENERATOR_MAP.put("notin", new NotinPredicateGenerator());
    }

    public static AbstractPredicateGenerator getGenerator(String oper) {
        oper = oper.toLowerCase();
        if (!PREDICATE_GENERATOR_MAP.containsKey(oper)) {
            return null;
        }
        return PREDICATE_GENERATOR_MAP.get(oper);
    }
}
