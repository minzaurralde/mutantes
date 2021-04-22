package uy.com.minza.mutantes.repository;

import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;
import uy.com.minza.mutantes.domain.Stats;

import java.util.List;

@Service
public class StatsRepository {

    private final MongoTemplate mongoTemplate;

    public StatsRepository(@Autowired MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public boolean existsById(final String id) {
        final CriteriaDefinition criteria = Criteria.where("_id").is(id);
        final Query query = Query.query(criteria);
        return this.mongoTemplate.exists(query, Stats.class);
    }

    @Cacheable("stats")
    public Stats findById(final String id) {
        final CriteriaDefinition criteria = Criteria.where("_id").is(id);
        final Query query = Query.query(criteria);
        final List<Stats> result = this.mongoTemplate.find(query, Stats.class);
        return result != null && result.size() == 1 ? result.get(0) : null;
    }

    public Stats save(final Stats stats) {
        return this.mongoTemplate.save(stats);
    }

    public boolean addResult(final String id, final boolean result) {
        final CriteriaDefinition criteria = Criteria.where("_id").is(id);
        final Query query = Query.query(criteria);
        final UpdateDefinition updateDefinition = Update.fromDocument(Document.parse("{ $inc: { " + (result ? "mutantCount" : "humanCount") + ": 1 } }"));
        final UpdateResult updateResult = this.mongoTemplate.upsert(query, updateDefinition, Stats.class);
        return updateResult.getModifiedCount() == 1;
    }
}
