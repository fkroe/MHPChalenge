package com.mhp.coding.challenges.mapping.models.db.blocks

import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto

open class ArticleBlock(
    override val sortIndex: Int
) : ArticleBlockDto
