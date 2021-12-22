package telran.b7a.forum.service.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j(topic = "Post servise")
@Service
public class ForumServiceLogging {
	
	@Pointcut("execution(public * "
			+ "telran.b7a.forum.service.ForumServiceImpl.find*(..))")
	public void bulkingFind() {}
	
	@Pointcut("execution(public * "
			+ "telran.b7a.forum.service.ForumServiceImpl.getPost(String))"
			+ " && args(id)")
	public void findById(String id) {}
	
	@Pointcut("@annotation(PostLogger) && args(.., id)")
	public void annoted(String id) {}
	
	@Around("bulkingFind()")
	public Object bulkingFindLogging(ProceedingJoinPoint pjp) throws Throwable{
		Object[] args = pjp.getArgs();
		long t1 = System.currentTimeMillis();
		Object retVal = pjp.proceed(args);
		long t2 = System.currentTimeMillis();
		log.info("metod = {}, duration = {}", pjp.getSignature().toLongString(), (t2-t1));
		return retVal;
	}
	
	@Before("findById(id)")
	public void getPostLogging(String id) {
		log.info("post with id {} requested", id);
	}
	
	@AfterReturning("annoted(id)")
	public void updatePostLoggin(String id) {
		log.info("post with id {} updated", id);
	}
}
