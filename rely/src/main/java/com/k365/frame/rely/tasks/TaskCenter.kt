package com.k365.frame.rely.tasks

import com.k365.frame.rely.childCoroutine
import com.k365.frame.rely.weakRefrence
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.Continuation

/**
 * 创建一个同步任务执行块
 * @param dispatcher 任务块所使用的协程作用域
 * @param block 任务块，执行后返回R
 */
@Deprecated(
    "Not recommended for use", ReplaceWith(
        "chain {  }.then {  }.end {  }.call(dispatcher)",
        "com.k365.frame.rely.chain.chain",
        "com.k365.frame.rely.chain.then",
        "com.k365.frame.rely.chain.end"
    ), DeprecationLevel.WARNING
)
fun <T, R> T.task(
    dispatcher: CoroutineDispatcher = childCoroutine,
    block: suspend T.() -> R
): Task<R> = SimpleTask(dispatcher, this.weakRefrence()(), block)

/**
 * 创建一个异步任务执行块
 * @param dispatcher 任务块所使用的协程作用域
 * @param block 任务块，执行前传入提供一个方法块参数，该参数用于手动完成任务。
 */
@Deprecated(
    "Not recommended for use", ReplaceWith(
        "chain {  }.then {  }.end {  }.call(dispatcher)",
        "com.k365.frame.rely.chain.chain",
        "com.k365.frame.rely.chain.then",
        "com.k365.frame.rely.chain.end"
    ), DeprecationLevel.WARNING
)
fun <T, R> T.call(
    dispatcher: CoroutineDispatcher = childCoroutine,
    block: T.(Continuation<R>) -> Unit
): Task<R> = AsyncTask(dispatcher, this.weakRefrence()(), block)

@Deprecated(
    "Not recommended for use", ReplaceWith(
        "chain {  }.then {  }.end {  }.call(dispatcher)",
        "com.k365.frame.rely.chain.chain",
        "com.k365.frame.rely.chain.then",
        "com.k365.frame.rely.chain.end"
    ), DeprecationLevel.WARNING
)
fun <R> call(
    dispatcher: CoroutineDispatcher = childCoroutine,
    block: (Continuation<R>) -> Unit
): Task<R> = CallTask(dispatcher, block)

@Deprecated("Not recommended for use", level = DeprecationLevel.WARNING)
class TimeOutException : RuntimeException("time out")


