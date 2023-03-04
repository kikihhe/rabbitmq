package com.xiaohe.basic.constant;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-03-04 10:41
 */
public interface RabbitMQ {
    /**
     * 所有交换机名称
     */
    interface EXCHANGE {
        /**
         * 普通交换机
         */
        public static final String NORMAL_EXCHANGE = "normal_exchange";

        /**
         * 死信交换机
         */
        public static final String DEAD_EXCHANGE = "dead_exchange";
    }

    /**
     * 所有队列的名称
     */
    interface QUEUE {
        /**
         * 普通队列
         */
        public static final String NORMAL_QUEUE = "normal_queue";

        /**
         * 死信队列
         */
        public static final String DEAD_QUEUE = "dead_queue";
    }
    interface ROUTING_KEY {
        /**
         * 普通队列的路由键
         */
        public static final String NORMAL_QUEUE_ROUTING_KEY = "normal_queue_routing_key";

        /**
         * 死信队列的路由键
         */
        public static final String DEAD_QUEUE_ROUTING_KEY = "dead_queue_routing_key";
    }
}
