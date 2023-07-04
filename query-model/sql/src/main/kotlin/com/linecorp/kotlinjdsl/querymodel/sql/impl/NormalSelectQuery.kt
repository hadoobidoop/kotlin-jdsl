package com.linecorp.kotlinjdsl.querymodel.sql.impl

import com.linecorp.kotlinjdsl.Internal
import com.linecorp.kotlinjdsl.querymodel.sql.*

@Internal
data class NormalSelectQuery(
    val select: Collection<Expression<*>>,
    val distinct: Boolean,
    val from: Collection<Table<*>>?,
    val where: Predicate?,
    val groupBy: Collection<Expression<*>>?,
    val having: Predicate?,
    val orderBy: Collection<Sort>?,
    val offset: Int?,
    val limit: Int?,
) : SelectQuery