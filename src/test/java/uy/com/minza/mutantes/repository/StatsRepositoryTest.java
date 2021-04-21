package uy.com.minza.mutantes.repository;

import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import uy.com.minza.mutantes.domain.Stats;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;
    @Autowired
    @InjectMocks
    private StatsRepository statsRepository;

    private Stats stats;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.clearInvocations(this.mongoTemplate);
        stats = Stats.builder().humanCount(2).mutantCount(1).build();
        final Query queryEqId = Query.query(Criteria
                .where("_id").is("id"));
        final Query queryEqOtherId = Query.query(Criteria
                .where("_id").is("other"));
        Mockito.when(this.mongoTemplate.exists(Mockito.eq(queryEqId), Mockito.eq(Stats.class)))
                .thenReturn(true);
        Mockito.when(this.mongoTemplate.find(Mockito.eq(queryEqId), Mockito.eq(Stats.class)))
                .thenReturn(Collections.singletonList(Stats.builder().humanCount(2).mutantCount(1).build()));
        Mockito.when(this.mongoTemplate.find(Mockito.eq(queryEqOtherId), Mockito.eq(Stats.class)))
                .thenReturn(null);
        Mockito.when(this.mongoTemplate.save(Mockito.eq(stats)))
                .thenReturn(stats);
        Mockito.when(this.mongoTemplate.upsert(Mockito.eq(queryEqId), Mockito.any(UpdateDefinition.class), Mockito.eq(Stats.class)))
                .thenReturn(UpdateResult.acknowledged(1, 1L, null));
        Mockito.when(this.mongoTemplate.upsert(Mockito.eq(queryEqOtherId), Mockito.any(UpdateDefinition.class), Mockito.eq(Stats.class)))
                .thenReturn(UpdateResult.acknowledged(0, 0L, null));
    }

    @Test
    void existsByIdTest() {
        Assertions.assertTrue(this.statsRepository.existsById("id"));
    }

    @Test
    void findByIdOKTest() {
        Assertions.assertNotNull(this.statsRepository.findById("id"));
    }

    @Test
    void findByIdNotOKTest() {
        Assertions.assertNull(this.statsRepository.findById("other"));
    }

    @Test
    void saveTest() {
        Assertions.assertNotNull(this.statsRepository.save(stats));
    }

    @Test
    void addResultTrueTest() {
        Assertions.assertTrue(this.statsRepository.addResult("id", true));
    }

    @Test
    void addResultFalseTest() {
        Assertions.assertTrue(this.statsRepository.addResult("id", false));
    }

    @Test
    void addResultNoUpsertTest() {
        Assertions.assertFalse(this.statsRepository.addResult("other", false));
    }
}