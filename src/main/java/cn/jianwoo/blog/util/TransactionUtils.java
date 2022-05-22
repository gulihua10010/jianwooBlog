package cn.jianwoo.blog.util;

import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-09 15:26
 */
@Component
@Slf4j
public class TransactionUtils {

    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;

    public void doTriggerTaskAfterCommit(Long taskId) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    log.info(">> Transactional afterCommit...");
                    asyncAutoTaskBaseService.doTriggerTask(taskId);
                }
            });
        } else {
            log.info(">> Transaction synchronization is not active");
            asyncAutoTaskBaseService.doTriggerTask(taskId);

        }

    }

    public void afterCommit(Function function) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    log.info(">> Transactional afterCommit...");
                    try {
                        function.apply();
                    } catch (Exception e) {
                        log.error(">> Transaction after commit process execute failure.", e);
                    }
                }
            });
        } else {
            log.info(">> Transaction synchronization is not active");
            try {
                function.apply();
            } catch (Exception e) {
                log.error(">> Transaction after commit process execute failure.", e);
            }
        }

    }

    public void afterCompletion(Function function) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCompletion(int status) {
                    log.info(">> Transactional afterCompletion...");
                    try {
                        function.apply();
                    } catch (Exception e) {
                        log.error(">> Transaction after completion process execute failure.", e);
                    }
                }
            });
        } else {
            log.info(">> Transaction synchronization is not active");
            try {
                function.apply();
            } catch (Exception e) {
                log.error(">> Transaction after commit process execute failure.", e);
            }
        }

    }

    @FunctionalInterface
    public interface Function {
        /**
         * Applies this function.
         *
         * @throws Exception e
         */
        void apply() throws Exception;
    }
}
