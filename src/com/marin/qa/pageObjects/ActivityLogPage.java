package com.marin.qa.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.marin.qa.pageObjects.pages.AbstractPage;

public class ActivityLogPage extends AbstractPage {

    static Logger log = Logger.getLogger(ActivityLogPage.class);
    private static ActivityLogPage instance;

    /**
     * Private constructor prevents construction outside this class.
     */
    private ActivityLogPage() {
    }

    public static synchronized ActivityLogPage getInstance() {

        if (instance == null) {
            instance = new ActivityLogPage();
        }

        return instance;
    }

    /**
     * Label Element as list of all labels on bubble
     * 
     * @version 2.00
     * @param locator
     *        as jQuery mapping for Label element
     * @param description
     *        as description for Label element
     * @author Michael Beider
     */
    public static enum Label {

        NoDataAlert(".dataGridNotice", "No Data Alert");

        private String locator;
        private String description;

        private Label(String locator, String description) {
            this.locator = locator;
            this.description = description;
        }

        public String getLocator() {
            return this.locator;
        }

        @Override
        public String toString() {
            return this.description;
        }

    }

    /**
     * DropDownMenu Element as list of all drop down menus on page
     * 
     * @version 2.00
     * @param locator
     *        as jQuery mapping for DropDown Menu element locator
     * @param spinner
     *        as jQuery mapping for spinner
     * @param pageLoad
     *        as jQuery mapping for pageLoad
     * @param description
     *        as description for DropDownMenu element
     * @author Michael Beider
     */
    public static enum DropDownMenu {

        Show("#operation_table_row_select", "#grid_overlay_operation_table", false, "Show");

        private String locator;
        private String spinner;
        private boolean pageLoad;
        private String description;

        private DropDownMenu(String locator, String spinner, boolean pageLoad, String description) {
            this.locator = locator;
            this.spinner = spinner;
            this.pageLoad = pageLoad;
            this.description = description;
        }

        public String getLocator() {
            return this.locator;
        }

        public String getSpinner() {
            return this.spinner;
        }

        public boolean getPageLoad() {
            return this.pageLoad;
        }

        @Override
        public String toString() {
            return this.description;
        }

    }

    /**
     * Link Element as list of all links on page
     * 
     * @version 2.00
     * @param locator
     *        as jQuery mapping for Link element locator
     * @param spinner
     *        as jQuery mapping for spinner
     * @param pageLoad
     *        as jQuery mapping for pageLoad
     * @param description
     *        as description for Link element
     * @author Michael Beider
     */
    public static enum Link {

        Campaigns("#bulk_upload_links_container a:contains(\"Campaigns\")", null, true, "Campaigns"), Groups("#bulk_upload_links_container a:contains(\"Groups\")", null, true, "Groups"), Keywords(
                "#bulk_upload_links_container a:contains(\"Keywords\")", null, true, "Keywords"), NegativeKeywords("#bulk_upload_links_container a:contains(\"NegativeKeywords\")", null, true,
                "NegativeKeywords"), Creatives("#bulk_upload_links_container a:contains(\"Creatives\")", null, true, "Creatives"), Placements(
                "#bulk_upload_links_container a:contains(\"Placements\")", null, true, "Placements"), Sitelinks("#bulk_upload_links_container a:contains(\"Sitelinks\")", null, true, "Sitelinks"), ProductGroups(
                "#bulk_upload_links_container a:contains(\"Product Groups\")", null, true, "Product Groups"), Folders("#bulk_upload_links_container a:contains(\"Folders\")", null, true, "Folders"), Revenue(
                "#bulk_upload_links_container a:contains(\"Revenue\")", null, true, "Revenue");

        private String locator;
        private String spinner;
        private boolean pageLoad;
        private String description;

        private Link(String locator, String spinner, boolean pageLoad, String description) {
            this.locator = locator;
            this.spinner = spinner;
            this.pageLoad = pageLoad;
            this.description = description;
        }

        public String getLocator() {
            return this.locator;
        }

        public String getSpinner() {
            return this.spinner;
        }

        public boolean getPageLoad() {
            return this.pageLoad;
        }

        @Override
        public String toString() {
            return this.description;
        }

    }

    /**
     * Button Element as list of all buttons on page
     * 
     * @version 2.00
     * @param locator
     *        as jQuery mapping for Button element locator
     * @param spinner
     *        as jQuery mapping for spinner
     * @param pageLoad
     *        as jQuery mapping for pageLoad
     * @param description
     *        as description for Button element
     * @author Michael Beider
     */
    public static enum Button {

        PostNow("#setup_action_sendcartop", "#grid_overlay_operation_table, #progress_grid_container", false, "Post Now"), Hold("#setup_action_pausecartop", null, false, "Hold"), Cancel(
                "#setup_action_cancel", null, false, "Cancel"), PostAllNow("#setup_action_postallnow", null, false, "Post All Now");

        private String locator;
        private String spinner;
        private boolean pageLoad;
        private String description;

        private Button(String locator, String spinner, boolean pageLoad, String description) {
            this.locator = locator;
            this.spinner = spinner;
            this.pageLoad = pageLoad;
            this.description = description;

        }

        public String getLocator() {
            return this.locator;
        }

        public String getId() {
            return this.locator.replace("#", "id=");
        }

        public String getSpinner() {
            return this.spinner;
        }

        public boolean getPageLoad() {
            return this.pageLoad;
        }

        @Override
        public String toString() {
            return this.description;
        }

    }

    /**
     * This Method set to click on link on page
     * 
     * @author mmadhusoodan
     * @param selenium
     * @param link
     * @return void
     * 
     */

    public void click(WebDriver driver, Link link) {
        String query = "$('" + link.getLocator() + "')[0].click();";
        if (isElementPresent(driver, link.getLocator())) {
            changeElementBackground(driver, link.getLocator());
            String retval = (String) ((JavascriptExecutor) driver).executeScript(query);
            removeElementBackground(driver, link.getLocator());
        }
        if (link.getPageLoad()) {
            waitForPageToLoad(driver, LONG_PAGE_TIMEOUT);
        }

        if (link.getSpinner() != null) {
            waitForElementNotToBeVisible(driver, link.getSpinner());
        }

    }
}
