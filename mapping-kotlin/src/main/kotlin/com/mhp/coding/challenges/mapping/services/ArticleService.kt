package com.mhp.coding.challenges.mapping.services

import com.mhp.coding.challenges.mapping.repositories.ArticleRepository
import com.mhp.coding.challenges.mapping.mappers.ArticleMapper
import com.mhp.coding.challenges.mapping.models.db.Article
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ArticleService(
    private val mapper: ArticleMapper,
) {
    fun list(): List<ArticleDto> {
        val articles = ArticleRepository.all()
        val articleDTOs = mutableListOf<ArticleDto>()
        for (article in articles) {
            val sortedArticleBlocks = sortArticleBlocks(article.blocks)
            articleDTOs.add(ArticleDto(article.id, article.title, article.description!!, article.author!!, sortedArticleBlocks))
        }
        return articleDTOs
    }

    fun articleForId(id: Long): ArticleDto {
        val article : Article
        try {
            article = ArticleRepository.findBy(id)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found!")
        }
        val sortedArticleBlocks = sortArticleBlocks(article.blocks)
        return ArticleDto(article.id, article.title, article.description!!, article.author!!, sortedArticleBlocks)
    }

    fun create(articleDto: ArticleDto): ArticleDto {
        val article = mapper.map(articleDto)
        ArticleRepository.create(article)
        return mapper.map(article)
    }

    fun sortArticleBlocks(articleBlocks: Set<ArticleBlock>): Set<ArticleBlock> {
        return articleBlocks.sortedBy { it.sortIndex }.toSet()
    }
}
