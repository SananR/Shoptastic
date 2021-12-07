package me.shoptastic.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.shoptastic.app.data.firebase.StoreRepository;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.presenter.LoginPresenter;
import me.shoptastic.app.data.presenter.RegisterCustomerPresenter;
import me.shoptastic.app.data.presenter.RegisterOwnerPresenter;
import me.shoptastic.app.ui.LoginActivity;
import me.shoptastic.app.ui.OwnerRegisterActivity;
import me.shoptastic.app.ui.RegisterActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    RegisterActivity registerUser;

    @Mock
    LoginActivity loginActivity;

    @Mock
    OwnerRegisterActivity registerOwner;

    @Mock
    StoreRepository storeRepository;

    @Mock
    UserRepository userRepository;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void showErrorMsg() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, null);
        customerPresenter.validateInput();
        verify(registerUser).error(null, null, null, "Password must be longer than 8");
    }

    @Test
    public void testValidateUserRegister() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.validateUserRegister(false);
        verify(userRepository).validateUserRegister(customerPresenter, "1@gmail.com", false);
    }

    @Test
    public void testRegister() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("12345679###");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(userRepository).register(new Customer("1@gmail.com", "hello", "+12324", "12345679###"));
    }

    @Test
    public void testGetEmailValidate() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.validateUserRegister(false);
        verify(registerUser).getEmail();
    }

    @Test
    public void testGetValidName() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getName();
    }

    @Test
    public void testGetInvalidName() {
        when(registerUser.getName()).thenReturn("h");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getName();
    }

    @Test
    public void testGetValidEmail() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getEmail();
    }

    @Test
    public void testGetInvalidEmail() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1aaa");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getEmail();
    }

    @Test
    public void testGetValidPhone() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getPhone();
    }

    @Test
    public void testGetInvalidPhone() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("qwrqer1");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getPhone();
    }


    @Test
    public void testGetValidPassword() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getPassword();
    }

    @Test
    public void testGetInvalidPassword() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getPassword();
    }

    @Test
    public void testGetInvalidPassword2() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("12346725367");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(false);
        verify(registerUser).getPassword();
    }

    @Test
    public void testUserExists() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("12346725367");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.errorUserExists();
        verify(registerUser).error(null, "Email already in use", null, null);
    }

    @Test
    public void testGetStoreName() {
        when(registerOwner.getStoreName()).thenReturn("hello");
        when(registerOwner.getAddress()).thenReturn("1@gmail.com");

        RegisterOwnerPresenter customerPresenter = new RegisterOwnerPresenter(registerOwner, userRepository, storeRepository);
        customerPresenter.validateInput();
        verify(registerOwner).getStoreName();
    }

    @Test
    public void testGetStoreAddress() {
        when(registerOwner.getStoreName()).thenReturn("hello");
        when(registerOwner.getAddress()).thenReturn("1@gmail.com");

        RegisterOwnerPresenter customerPresenter = new RegisterOwnerPresenter(registerOwner, userRepository, storeRepository);
        customerPresenter.validateInput();
        verify(registerOwner).getAddress();
    }

    @Test
    public void testGetOwnerName() {

        when(registerOwner.getName()).thenReturn("hello");
        when(registerOwner.getEmail()).thenReturn("1@gmail.com");
        when(registerOwner.getPassword()).thenReturn("hello");
        when(registerOwner.getPhone()).thenReturn("hello");
        when(registerOwner.getStore()).thenReturn(new Store());
        when(registerOwner.getAddress()).thenReturn("hello");
        when(registerOwner.getStoreName()).thenReturn("hello");

        RegisterOwnerPresenter customerPresenter = new RegisterOwnerPresenter(registerOwner, userRepository, storeRepository);
        customerPresenter.register();
        verify(registerOwner).getName();
    }

    @Test
    public void testGetOwnerEmail() {

        when(registerOwner.getName()).thenReturn("hello");
        when(registerOwner.getEmail()).thenReturn("1@gmail.com");
        when(registerOwner.getPassword()).thenReturn("hello");
        when(registerOwner.getPhone()).thenReturn("hello");
        when(registerOwner.getStore()).thenReturn(new Store());
        when(registerOwner.getAddress()).thenReturn("hello");
        when(registerOwner.getStoreName()).thenReturn("hello");

        RegisterOwnerPresenter customerPresenter = new RegisterOwnerPresenter(registerOwner, userRepository, storeRepository);
        customerPresenter.register();
        verify(registerOwner).getEmail();
    }

    @Test
    public void testGetOwnerPhone() {

        when(registerOwner.getName()).thenReturn("hello");
        when(registerOwner.getEmail()).thenReturn("1@gmail.com");
        when(registerOwner.getPassword()).thenReturn("hello");
        when(registerOwner.getPhone()).thenReturn("hello");
        when(registerOwner.getStore()).thenReturn(new Store());
        when(registerOwner.getAddress()).thenReturn("hello");
        when(registerOwner.getStoreName()).thenReturn("hello");

        RegisterOwnerPresenter customerPresenter = new RegisterOwnerPresenter(registerOwner, userRepository, storeRepository);
        customerPresenter.register();
        verify(registerOwner).getPhone();
    }

    @Test
    public void testGetOwnerPassword() {

        when(registerOwner.getName()).thenReturn("hello");
        when(registerOwner.getEmail()).thenReturn("1@gmail.com");
        when(registerOwner.getPassword()).thenReturn("hello");
        when(registerOwner.getPhone()).thenReturn("hello");
        when(registerOwner.getStore()).thenReturn(new Store());
        when(registerOwner.getAddress()).thenReturn("hello");
        when(registerOwner.getStoreName()).thenReturn("hello");

        RegisterOwnerPresenter customerPresenter = new RegisterOwnerPresenter(registerOwner, userRepository, storeRepository);
        customerPresenter.register();
        verify(registerOwner).getPassword();
    }

    @Test
    public void testGetOwnerStore() {

        when(registerOwner.getName()).thenReturn("hello");
        when(registerOwner.getEmail()).thenReturn("1@gmail.com");
        when(registerOwner.getPassword()).thenReturn("hello");
        when(registerOwner.getPhone()).thenReturn("hello");
        when(registerOwner.getStore()).thenReturn(new Store());
        when(registerOwner.getAddress()).thenReturn("hello");
        when(registerOwner.getStoreName()).thenReturn("hello");

        RegisterOwnerPresenter customerPresenter = new RegisterOwnerPresenter(registerOwner, userRepository, storeRepository);
        customerPresenter.register();
        verify(registerOwner).getStore();
    }

    @Test
    public void testRegisterOwner() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterCustomerPresenter customerPresenter = new RegisterCustomerPresenter(registerUser, userRepository);
        customerPresenter.complete(true);
        verify(registerUser).getName();
    }


    @Test
    public void testLoginGetEmail() {
        when(loginActivity.getEmail()).thenReturn("a");
        when(loginActivity.getPassword()).thenReturn("b");
        LoginPresenter presenter = new LoginPresenter(loginActivity, userRepository);
        presenter.login();
        verify(loginActivity).getEmail();
    }

    @Test
    public void testLoginGetPassword() {
        when(loginActivity.getEmail()).thenReturn("a");
        when(loginActivity.getPassword()).thenReturn("b");
        LoginPresenter presenter = new LoginPresenter(loginActivity, userRepository);
        presenter.login();
        verify(loginActivity).getPassword();
    }

    @Test
    public void testLoginSuccess() {
        when(loginActivity.getEmail()).thenReturn("a");
        when(loginActivity.getPassword()).thenReturn("b");
        LoginPresenter presenter = new LoginPresenter(loginActivity, userRepository);
        Customer user = new Customer("", "", "", "");
        presenter.onLoginSuccess(user);
        verify(userRepository).setLoggedInUser(user);
    }

    @Test
    public void testLoginFailed() {
        when(loginActivity.getEmail()).thenReturn("a");
        when(loginActivity.getPassword()).thenReturn("b");
        LoginPresenter presenter = new LoginPresenter(loginActivity, userRepository);
        System.out.println(new IllegalArgumentException("err").getMessage());
        presenter.onLoginFailed(new Result.Error(new IllegalArgumentException("err")));
        verify(loginActivity).error("err");
    }

}