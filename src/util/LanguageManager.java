package util;

import java.util.HashMap;

public class LanguageManager {

    private static String currentLanguage = "EN";

    private static final HashMap<String, HashMap<String, String>> translations = new HashMap<>();

    static {

        HashMap<String, String> en = new HashMap<>();
        en.put("login_title", "Hospital Login");
        en.put("username", "Username:");
        en.put("password", "Password:");
        en.put("role", "Role:");
        en.put("language", "Language:");
        en.put("login", "Login");
        en.put("exit", "Exit");
        en.put("login_success", "Login successful!");
        en.put("login_error", "Invalid username, password or role.");

        en.put("app_title", "Hospital Management System");
        en.put("dashboard", "Dashboard");
        en.put("patients", "Patients");
        en.put("doctors", "Doctors");
        en.put("appointments", "Appointments");
        en.put("theme", "🌙 Theme");

        en.put("dashboard_title", "Hospital Dashboard");
        en.put("total_patients", "Total Patients");
        en.put("total_doctors", "Total Doctors");
        en.put("total_appointments", "Total Appointments");
        en.put("records_chart", "Hospital Records Chart");
        en.put("category", "Category");
        en.put("total_count", "Total Count");
        en.put("count", "Count");
        en.put("refresh_dashboard", "Refresh Dashboard");

        en.put("patient_management", "Patient Management");
        en.put("patient_form", "Patient Form");
        en.put("name", "Name:");
        en.put("age", "Age:");
        en.put("address", "Address:");
        en.put("payment", "Payment:");
        en.put("add_patient", "Add Patient");
        en.put("clear", "Clear");
        en.put("search", "Search");
        en.put("show_all", "Show All");
        en.put("refresh", "Refresh");
        en.put("update_selected", "Update Selected");
        en.put("delete_selected", "Delete Selected");

        en.put("patient_management","Patient Management");
        en.put("patient_form","Patient Form");
        en.put("name","Name:");
        en.put("age","Age:");
        en.put("address","Address:");
        en.put("payment","Payment:");
        en.put("add_patient","Add Patient");
        en.put("clear","Clear");
        en.put("search","Search");
        en.put("show_all","Show All");
        en.put("refresh","Refresh");
        en.put("update_selected","Update Selected");
        en.put("delete_selected","Delete Selected");

        en.put("patient_added", "Patient added successfully!");
        en.put("patient_updated", "Patient updated successfully!");
        en.put("patient_deleted", "Patient deleted successfully!");
        en.put("invalid_patient", "Please enter valid patient information.");
        en.put("select_patient", "Please select a patient from the table.");


        HashMap<String, String> tr = new HashMap<>();
        tr.put("login_title", "Hastane Girişi");
        tr.put("username", "Kullanıcı Adı:");
        tr.put("password", "Şifre:");
        tr.put("role", "Rol:");
        tr.put("language", "Dil:");
        tr.put("login", "Giriş");
        tr.put("exit", "Çıkış");
        tr.put("login_success", "Giriş başarılı!");
        tr.put("login_error", "Kullanıcı adı, şifre veya rol hatalı.");

        tr.put("app_title", "Hastane Yönetim Sistemi");
        tr.put("dashboard", "Panel");
        tr.put("patients", "Hastalar");
        tr.put("doctors", "Doktorlar");
        tr.put("appointments", "Randevular");
        tr.put("theme", "🌙 Tema");

        tr.put("dashboard_title", "Hastane Paneli");
        tr.put("total_patients", "Toplam Hasta");
        tr.put("total_doctors", "Toplam Doktor");
        tr.put("total_appointments", "Toplam Randevu");
        tr.put("records_chart", "Hastane Kayıt Grafiği");
        tr.put("category", "Kategori");
        tr.put("total_count", "Toplam Sayı");
        tr.put("count", "Sayı");
        tr.put("refresh_dashboard", "Paneli Yenile");

        tr.put("patient_management", "Hasta Yönetimi");
        tr.put("patient_form", "Hasta Formu");
        tr.put("name", "İsim:");
        tr.put("age", "Yaş:");
        tr.put("address", "Adres:");
        tr.put("payment", "Ödeme:");
        tr.put("add_patient", "Hasta Ekle");
        tr.put("clear", "Temizle");
        tr.put("search", "Ara");
        tr.put("show_all", "Tümünü Göster");
        tr.put("refresh", "Yenile");
        tr.put("update_selected", "Seçileni Güncelle");
        tr.put("delete_selected", "Seçileni Sil");

        tr.put("patient_management","Hasta Yönetimi");
        tr.put("patient_form","Hasta Formu");
        tr.put("name","İsim:");
        tr.put("age","Yaş:");
        tr.put("address","Adres:");
        tr.put("payment","Ödeme:");
        tr.put("add_patient","Hasta Ekle");
        tr.put("clear","Temizle");
        tr.put("search","Ara");
        tr.put("show_all","Tümünü Göster");
        tr.put("refresh","Yenile");
        tr.put("update_selected","Seçileni Güncelle");
        tr.put("delete_selected","Seçileni Sil");

        tr.put("patient_added", "Hasta başarıyla eklendi!");
        tr.put("patient_updated", "Hasta başarıyla güncellendi!");
        tr.put("patient_deleted", "Hasta başarıyla silindi!");
        tr.put("invalid_patient", "Lütfen geçerli hasta bilgileri girin.");
        tr.put("select_patient", "Lütfen tablodan bir hasta seçin.");

        HashMap<String, String> az = new HashMap<>();
        az.put("login_title", "Xəstəxana Girişi");
        az.put("username", "İstifadəçi adı:");
        az.put("password", "Şifrə:");
        az.put("role", "Rol:");
        az.put("language", "Dil:");
        az.put("login", "Giriş");
        az.put("exit", "Çıxış");
        az.put("login_success", "Giriş uğurludur!");
        az.put("login_error", "İstifadəçi adı, şifrə və ya rol yanlışdır.");

        az.put("app_title", "Xəstəxana İdarəetmə Sistemi");
        az.put("dashboard", "Panel");
        az.put("patients", "Xəstələr");
        az.put("doctors", "Həkimlər");
        az.put("appointments", "Görüşlər");
        az.put("theme", "🌙 Mövzu");

        az.put("dashboard_title", "Xəstəxana Paneli");
        az.put("total_patients", "Ümumi Xəstə");
        az.put("total_doctors", "Ümumi Həkim");
        az.put("total_appointments", "Ümumi Görüş");
        az.put("records_chart", "Xəstəxana Qeyd Qrafiki");
        az.put("category", "Kateqoriya");
        az.put("total_count", "Ümumi Say");
        az.put("count", "Say");
        az.put("refresh_dashboard", "Paneli Yenilə");

        az.put("patient_management","Xəstə İdarəetməsi");
        az.put("patient_form","Xəstə Formu");
        az.put("name","Ad:");
        az.put("age","Yaş:");
        az.put("address","Ünvan:");
        az.put("payment","Ödəniş:");
        az.put("add_patient","Xəstə Əlavə Et");
        az.put("clear","Təmizlə");
        az.put("search","Axtar");
        az.put("show_all","Hamısını Göstər");
        az.put("refresh","Yenilə");
        az.put("update_selected","Seçiləni Yenilə");
        az.put("delete_selected","Seçiləni Sil");

        az.put("patient_added", "Xəstə uğurla əlavə edildi!");
        az.put("patient_updated", "Xəstə uğurla yeniləndi!");
        az.put("patient_deleted", "Xəstə uğurla silindi!");
        az.put("invalid_patient", "Zəhmət olmasa düzgün xəstə məlumatları daxil edin.");
        az.put("select_patient", "Zəhmət olmasa cədvəldən xəstə seçin.");

        HashMap<String, String> ru = new HashMap<>();
        ru.put("login_title", "Вход в больничную систему");
        ru.put("username", "Имя пользователя:");
        ru.put("password", "Пароль:");
        ru.put("role", "Роль:");
        ru.put("language", "Язык:");
        ru.put("login", "Войти");
        ru.put("exit", "Выход");
        ru.put("login_success", "Вход выполнен успешно!");
        ru.put("login_error", "Неверное имя пользователя, пароль или роль.");

        ru.put("app_title", "Система управления больницей");
        ru.put("dashboard", "Панель");
        ru.put("patients", "Пациенты");
        ru.put("doctors", "Врачи");
        ru.put("appointments", "Приёмы");
        ru.put("theme", "🌙 Тема");

        ru.put("dashboard_title", "Панель больницы");
        ru.put("total_patients", "Всего пациентов");
        ru.put("total_doctors", "Всего врачей");
        ru.put("total_appointments", "Всего приёмов");
        ru.put("records_chart", "График больничных записей");
        ru.put("category", "Категория");
        ru.put("total_count", "Общее количество");
        ru.put("count", "Количество");
        ru.put("refresh_dashboard", "Обновить панель");

        ru.put("patient_management","Управление пациентами");
        ru.put("patient_form","Форма пациента");
        ru.put("name","Имя:");
        ru.put("age","Возраст:");
        ru.put("address","Адрес:");
        ru.put("payment","Оплата:");
        ru.put("add_patient","Добавить пациента");
        ru.put("clear","Очистить");
        ru.put("search","Поиск");
        ru.put("show_all","Показать все");
        ru.put("refresh","Обновить");
        ru.put("update_selected","Обновить выбранное");
        ru.put("delete_selected","Удалить выбранное");

        ru.put("patient_added", "Пациент успешно добавлен!");
        ru.put("patient_updated", "Пациент успешно обновлён!");
        ru.put("patient_deleted", "Пациент успешно удалён!");
        ru.put("invalid_patient", "Пожалуйста, введите корректные данные пациента.");
        ru.put("select_patient", "Пожалуйста, выберите пациента из таблицы.");

        translations.put("EN", en);
        translations.put("TR", tr);
        translations.put("AZ", az);
        translations.put("RU", ru);
    }

    public static void setLanguage(String language) {
        currentLanguage = language;
    }

    public static String getLanguage() {
        return currentLanguage;
    }

    public static String get(String key) {
        return translations
                .getOrDefault(currentLanguage, translations.get("EN"))
                .getOrDefault(key, key);
    }
}