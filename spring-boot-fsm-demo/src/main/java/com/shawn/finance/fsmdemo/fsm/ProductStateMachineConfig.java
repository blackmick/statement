package com.shawn.finance.fsmdemo.fsm;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * Created by shawn on 15/12/28.
 */
@Configuration
@EnableStateMachine
public class ProductStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception{
        states.withStates()
                .initial(States.STATE_INIT)
                .end(States.STATE_NORMAL)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception{
        transitions.withExternal()
                .source(States.STATE_INIT)
                .target(States.STATE_APPLYING)
                .event(Events.EVENT_APPLY);
    }
}
