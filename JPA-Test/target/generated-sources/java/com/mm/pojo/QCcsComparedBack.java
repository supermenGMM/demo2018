package com.mm.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCcsComparedBack is a Querydsl query type for CcsComparedBack
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCcsComparedBack extends EntityPathBase<CcsComparedBack> {

    private static final long serialVersionUID = 648574192L;

    public static final QCcsComparedBack ccsComparedBack = new QCcsComparedBack("ccsComparedBack");

    public final StringPath channelDesc = createString("channelDesc");

    public final StringPath compareChannel = createString("compareChannel");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> returnCode = createNumber("returnCode", Integer.class);

    public final NumberPath<Integer> txnAmt = createNumber("txnAmt", Integer.class);

    public QCcsComparedBack(String variable) {
        super(CcsComparedBack.class, forVariable(variable));
    }

    public QCcsComparedBack(Path<? extends CcsComparedBack> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCcsComparedBack(PathMetadata metadata) {
        super(CcsComparedBack.class, metadata);
    }

}

