package com.njcit.concurrent4;

/**
 * @Author LiJun
 * @Date 2020/5/15 15:08
 * Condition因素出Object监视器方法（ wait ， notify和notifyAll ）到不同的对象，以得到具有多个等待集的每个对象，通过将它们与使用任意的组合的效果Lock实现。 凡Lock替换使用的synchronized方法和语句，一个Condition替换使用的对象监控方法。
 * 条件（也称为条件队列或条件变量 ）提供了一种手段，直到被另一个线程通知，一些国家现在的条件可能是真实的一个线程中止执行（“等待”）。 因为访问该共享状态信息在不同的线程时，它必须被保护，所以某些形式的锁与该条件相关联。 该等待条件提供的关键特性是它自动释放相关的锁，并挂起当前线程，就像Object.wait 。
 * 一个Condition实例本质绑定到锁。 为了获得一个Condition的实例为特定Lock实例利用其newCondition()方法。
 * 举个例子，假设我们有一个界缓冲区，它支持put和take的方法。 如果take尝试对一个空的缓冲器，则该线程将阻塞，直到一个项目变为可用; 如果put试图在一个完整的缓冲区，那么线程将阻塞，直到空间变得可用。 我们愿继续等待put线，并take线程独立的等待集，这样我们就可以使用的只有在当项目或空间的缓冲区变得可用时通知单个线程的优化。 这可以使用两个实现Condition的情况。
 *    class BoundedBuffer {
 *      final Lock lock = new ReentrantLock();
 *      final Condition notFull  = lock.newCondition();
 *      final Condition notEmpty = lock.newCondition();
 *
 *      final Object[] items = new Object[100];
 *      int putptr, takeptr, count;
 *
 *      public void put(Object x) throws InterruptedException {
 *        lock.lock();
 *        try {
 *          while (count == items.length)
 *            notFull.await();
 *          items[putptr] = x;
 *          if (++putptr == items.length) putptr = 0;
 *          ++count;
 *          notEmpty.signal();
 *        } finally {
 *          lock.unlock();
 *        }
 *      }
 *
 *      public Object take() throws InterruptedException {
 *        lock.lock();
 *        try {
 *          while (count == 0)
 *            notEmpty.await();
 *          Object x = items[takeptr];
 *          if (++takeptr == items.length) takeptr = 0;
 *          --count;
 *          notFull.signal();
 *          return x;
 *        } finally {
 *          lock.unlock();
 *        }
 *      }
 *    }
 *
 * （该java.util.concurrent.ArrayBlockingQueue类提供此功能，所以没有理由来实现此样品使用级）。
 * 甲Condition实现可以提供的行为和语义不同于的不同Object监视器的方法，如保证排序的通知，或不需要的锁被保持在执行通知时。 如果一个实现提供了这样特殊的语义，则该实现必须记录这些语义。
 * 需要注意的是Condition实例只是普通的对象，其本身作为一个目标synchronized语句，可以有自己的监视wait和notification方法调用。 获取的监视器锁定Condition实例，或利用其显示器的方法，具有获取没有指定的关系Lock与该相关联的Condition或使用其的等待和信令方法。 建议，为避免混淆，永远不要使用Condition实例以这种方式，其自身的实现中或许除外。
 * 除非另有说明，传递一个null值的任何参数将导致NullPointerException被抛出。
 * 实施注意事项
 * 当在等待Condition ，一个“ 虚假唤醒 ”被允许发生，一般来说，作为让步的基础平台语义。 这有一个对大多数应用程序的实际影响很小Condition应该总是在一个循环等待，测试正被等待的状态下断言。 实现是免费删除虚假唤醒的可能性，但建议应用程序的程序员总是假定他们可以发生，所以在循环总是等待。
 * 这三种形式的条件等待（可中断，不可中断和定时）可以其易于在某些平台上实现的，并在其性能特性不同。 特别是，它可能很难提供这些特性和维护特定语义，比如排序保证。 此外，中断线程的实际挂起的能力可能并不总是在所有平台中是可行的。
 * 因此，并不需要实现精确定义相同的保证或语义为所有三种形式的等待，也不是支持线程的实际挂起的中断，它要求。
 * 一个实现需要清楚地记录由每个等待方法提供的语义和保证，并且当实现不执行线程挂起的中断支持那么作为该接口中定义它必须服从中断语义。
 * 由于中断通常意味着取消，并进行中断检查往往是罕见的，一个实现可能更喜欢响应对于普通方法返回一个中断。 这是真实的，即使可以证明，在中断后可能会释放线程另一个动作发生了。 实现应记录此行为。
 */

public class MyTest3 {
}
