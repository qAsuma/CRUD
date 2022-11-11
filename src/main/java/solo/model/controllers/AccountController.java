package solo.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import solo.model.Account;
import solo.model.Client;
import solo.model.dao.AccountDAO;
import solo.model.dao.ClientDAO;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("acc")
public class AccountController {
    private final AccountDAO accountDAO;


    @Autowired
    public AccountController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;

    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("acc", accountDAO.index());
        return "indexAccount";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("client") Client client) {
        model.addAttribute("account", accountDAO.show(id));


        return "showAccount";
    }


//    @GetMapping("new")
//    public String newAccount(@ModelAttribute("account") Account account) {
//        return "newAccount";
//    }
//
//
//    @PostMapping()
//    public String create(@ModelAttribute("account") @Valid Account account,
//                         BindingResult bindingResult) {
////        personValidator.validate(client,bindingResult);
//
//        if (bindingResult.hasErrors())
//            return "newAccount";
//        accountDAO.save(account);
//        return "redirect:acc";
//    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("account", accountDAO.show(id));
        return "editAccount";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult,
                         @PathVariable("id") int id) {
//        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "editAccount";

        accountDAO.update(id, account);
        return "redirect:/acc";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        accountDAO.delete(id);
        return "redirect:/acc";
    }


}
