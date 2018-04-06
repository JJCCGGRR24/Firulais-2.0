//
// package usecases;
//
// import javax.transaction.Transactional;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import services.NewspaperService;
// import utilities.AbstractTest;
// import domain.Newspaper;
//
// @ContextConfiguration(locations = {
// "classpath:spring/junit.xml"
// })
// @RunWith(SpringJUnit4ClassRunner.class)
// @Transactional
// public class UseCase06_3Test extends AbstractTest {
//
// //SUT
//
// @Autowired
// private NewspaperService newspaperService;
//
//
// // 6. An actor who is authenticated as a user must be able to:
// // 3. Write an article and attach it to any newspaper that has not been published, yet.
// // Note that articles may be saved in draft mode, which allows to modify them later, or
// // final model, which freezes them forever.
//
// @Test
// public void driver() {
// final Object testingData[][] = {
// {
//
// //comprobaremos que un user puede publicar un article de este newspaper correctamente
// //ya que cumple todo los requisitos
// "user1", "newspaper1", null
// }, {
// //comprobaremos que un user al intentar publicar un article en este newspaper no se le permitirá
// //porque está ya en modo final
// "user2", "newspaper1", IllegalArgumentException.class
// }
//
// };
//
// for (int i = 0; i < testingData.length; i++)
// this.template((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), ((Class<?>) testingData[i][2]));
//
// }
// protected void template(final String username, final int newspaperId, final Class<?> expected) {
// Class<?> caught;
//
// caught = null;
// try {
// super.authenticate(username);
// final Newspaper news = this.newspaperService.findOne(newspaperId);
//
// this.newspaperService.flush();
//
// } catch (final Throwable oops) {
// caught = oops.getClass();
// }
//
// super.checkExceptions(expected, caught);
// super.unauthenticate();
//
// }
//}
