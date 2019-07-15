package io.dreamtale.mybatisplus.extend;


import java.util.HashMap;
import java.util.Map;

import io.dreamtale.mybatisplus.extend.generator.AbstractPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.EqPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.GePredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.GtPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.InPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.IsEmptyPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.IsNotEmptyPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.IsNotNullPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.IsNullPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.LePredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.LikePredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.LtPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.NePredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.NotLikePredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.NotinPredicateGenerator;
import io.dreamtale.mybatisplus.extend.generator.AbstractPredicateGenerator;

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
