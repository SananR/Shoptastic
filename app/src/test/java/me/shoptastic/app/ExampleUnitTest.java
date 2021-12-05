package me.shoptastic.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.data.register.presenter.RegisterPresenter;
import me.shoptastic.app.ui.register.RegisterActivity;

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
    RegisterPresenter presenter;

    @Mock
    RegisterRepository registerRepository;

    @Mock
    LoginRepository loginRepository;

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
        RegisterPresenter customerPresenter = new RegisterPresenter(registerUser, null, null);
        customerPresenter.register();
        verify(registerUser).error(null, null, null, "Password must be longer than 8");
    }

    @Test
    public void testGetName() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterPresenter customerPresenter = new RegisterPresenter(registerUser, null, null);
        customerPresenter.register();
        verify(registerUser).getName();
    }

    @Test
    public void testGetEmail() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterPresenter customerPresenter = new RegisterPresenter(registerUser, null, null);
        customerPresenter.register();
        verify(registerUser).getEmail();
    }

    @Test
    public void testGetPhone() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterPresenter customerPresenter = new RegisterPresenter(registerUser, null, null);
        customerPresenter.register();
        verify(registerUser).getPhone();
    }

    @Test
    public void testGetPassword() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+12324");
        when(registerUser.getPassword()).thenReturn("1234");
        RegisterPresenter customerPresenter = new RegisterPresenter(registerUser, null, null);
        customerPresenter.register();
        verify(registerUser).getPassword();
    }

    @Test
    public void testRegister() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+1234");
        when(registerUser.getPassword()).thenReturn("123456789###");
        Customer customer = new Customer("1@gmail.com", "hello", "+1234");
        when(registerRepository.register(any(Customer.class), anyString())).thenReturn(new Result.Success(customer));
        RegisterPresenter customerPresenter = new RegisterPresenter(registerUser, registerRepository, loginRepository);
        customerPresenter.register();
        verify(registerRepository).register(customer, "123456789###");
    }

    @Test
    public void testSetLoggedInUser() {
        when(registerUser.getName()).thenReturn("hello");
        when(registerUser.getEmail()).thenReturn("1@gmail.com");
        when(registerUser.getPhone()).thenReturn("+1234");
        when(registerUser.getPassword()).thenReturn("123456789###");
        Customer customer = new Customer("1@gmail.com", "hello", "+1234");
        when(registerRepository.register(any(Customer.class), anyString())).thenReturn(new Result.Success(customer));
        RegisterPresenter customerPresenter = new RegisterPresenter(registerUser, registerRepository, loginRepository);
        customerPresenter.register();
        verify(loginRepository).setLoggedInUser(customer);
    }

}