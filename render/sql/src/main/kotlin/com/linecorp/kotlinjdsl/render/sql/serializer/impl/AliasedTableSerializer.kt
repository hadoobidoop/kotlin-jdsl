package com.linecorp.kotlinjdsl.render.sql.serializer.impl

import com.linecorp.kotlinjdsl.querymodel.sql.impl.AliasedTable
import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.render.sql.serializer.SqlRenderClause
import com.linecorp.kotlinjdsl.render.sql.serializer.SqlRenderSerializer
import com.linecorp.kotlinjdsl.render.sql.serializer.SqlRenderStatement
import com.linecorp.kotlinjdsl.render.sql.serializer.SqlSerializer
import com.linecorp.kotlinjdsl.render.sql.writer.SqlWriter
import kotlin.reflect.KClass

class AliasedTableSerializer : SqlSerializer<AliasedTable<*>> {
    override fun handledType(): KClass<AliasedTable<*>> {
        return AliasedTable::class
    }

    override fun serialize(part: AliasedTable<*>, writer: SqlWriter, context: RenderContext) {
        val statement = context.getValue(SqlRenderStatement)
        val clause = context.getValue(SqlRenderClause)

        if (statement.isSelect() && clause.isFrom()) {
            val delegate = context.getValue(SqlRenderSerializer)

            delegate.serialize(part.table, writer, context)

            writer.writeKeyword("AS")
            writer.write(part.alias)
        } else {
            writer.write(part.alias)
        }
    }
}