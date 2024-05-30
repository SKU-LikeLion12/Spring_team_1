package likelion.helloworld.repository;

import likelion.helloworld.domain.Article;
import likelion.helloworld.domain.Comment;
import likelion.helloworld.domain.Member;

import java.util.List;

public interface CommentRepository {

    public Comment findById(Long id);
    public void saveComment(Comment comment);
    public void deleteComment(Comment comment);

    public List<Comment> findArticleComment(Article article);
    public List<Comment> findMemberComment(Member member);
    public List<Article> findMemberCommentArticle(Member member);

}
