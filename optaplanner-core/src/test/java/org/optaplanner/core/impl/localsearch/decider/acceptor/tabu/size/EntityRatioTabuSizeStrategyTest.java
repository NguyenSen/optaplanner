/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.core.impl.localsearch.decider.acceptor.tabu.size;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.optaplanner.core.impl.localsearch.scope.LocalSearchPhaseScope;
import org.optaplanner.core.impl.localsearch.scope.LocalSearchStepScope;
import org.optaplanner.core.impl.solver.scope.DefaultSolverScope;

public class EntityRatioTabuSizeStrategyTest {

    @Test
    public void tabuSize() {
        LocalSearchPhaseScope phaseScope = new LocalSearchPhaseScope(mock(DefaultSolverScope.class));
        when(phaseScope.getWorkingEntityCount()).thenReturn(100);
        LocalSearchStepScope stepScope = new LocalSearchStepScope(phaseScope);
        assertEquals(10, new EntityRatioTabuSizeStrategy(0.1).determineTabuSize(stepScope));
        assertEquals(50, new EntityRatioTabuSizeStrategy(0.5).determineTabuSize(stepScope));
        // Rounding
        assertEquals(11, new EntityRatioTabuSizeStrategy(0.1051).determineTabuSize(stepScope));
        assertEquals(10, new EntityRatioTabuSizeStrategy(0.1049).determineTabuSize(stepScope));
        // Corner cases
        assertEquals(1, new EntityRatioTabuSizeStrategy(0.0000001).determineTabuSize(stepScope));
        assertEquals(99, new EntityRatioTabuSizeStrategy(0.9999999).determineTabuSize(stepScope));
    }

}
