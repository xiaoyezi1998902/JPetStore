package persistence;

import domain.Sequence;

public interface SequenceDao {

    Sequence getSequence(Sequence var1);

    void updateSequence(Sequence var1);

}
