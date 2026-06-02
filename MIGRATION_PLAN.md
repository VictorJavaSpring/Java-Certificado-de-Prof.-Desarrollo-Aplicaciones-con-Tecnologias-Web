# MIGRATION_PLAN

Sorted from easiest to hardest migration.

## Migration Progress

### mostraProdCateg (Phase 1 — read-only)

| Item | Detail |
|------|--------|
| **JSP source** | `WebContent/WEB-INF/jsp/mostraProdCateg/mostraProdCateg.jsp` |
| **XHTML target** | `WebContent/views/mostraProdCateg.xhtml` |
| **Managed bean** | `com.soc.ewok.bean.MostraProdCategBean` |
| **Status** | Working |
| **DAO reused** | `ProducteDAO.obtenirTotsXTipusIdVigents(String codi)` |
| **Database access** | Working |
| **PrimeFaces page rendering** | Working |
| **Known issue** | Product images not displayed because `GFotoController` uses a hardcoded path (`/media/windows7/eWokFotos`) and throws `NullPointerException` in `getCurrentFile()`. |
| **Blocking** | No |
| **Priority** | Medium |

**Test URL (example):** `/eWok/views/mostraProdCateg.xhtml?codi=<tipusProducteCodi>`

Legacy servlet/JSP route remains unchanged: `/mostraProdCateg?codi=...`

---

| File path | Purpose | Controllers used | DAO dependencies | Complexity | Migration effort | Risk |
|---|---|---|---|---|---|---|
| `WebContent/WEB-INF/jsp/exemples/plantilla.jsp` | Template/example page | (none mapped) | (none) | Low | 1 day | Low |
| `WebContent/WEB-INF/jsp/home.jsp` | Home/landing view | HomeServlet | (none) | Low | 1 day | Low |
| `WebContent/WEB-INF/jsp/staff/accionopermesa.jsp` | Feature page | (none mapped) | (none) | Medium | 1 day | Medium |
| `WebContent/WEB-INF/jsp/staff/gOfertaComanda/veureOfertaComanda.jsp` | Detail view | GOfertaComandaController | OfertaComandaDAO | Medium | 1 day | Medium |
| `WebContent/WEB-INF/jsp/staff/gUnitat/veureGUnitat.jsp` | Detail view | (none mapped) | (none) | Medium | 1 day | Medium |
| `WebContent/WEB-INF/jsp/staff/homeClientsEsborrar.jsp` | Home/landing view | Exemple2StaffControllerEsborrar | (none) | Medium | 1 day | Medium |
| `WebContent/WEB-INF/jsp/staff/homeProductesEsborrar.jsp` | Home/landing view | Exemple1StaffControllerEsborrar | (none) | Medium | 1 day | Medium |
| `WebContent/WEB-INF/jsp/staff/homeUnitatEsborrar.jsp` | Home/landing view | (none mapped) | (none) | Medium | 1 day | Medium |
| `WebContent/WEB-INF/jsp/exemples/home.jsp` | Home/landing view | (none mapped) | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/imports/footerEwok.jsp` | Feature page | (none mapped) | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gOfertaProducte/veureOfertaProducte.jsp` | Detail view | (none mapped) | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gPreu/veurePreu.jsp` | Detail view | GPreuController | PreuDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gRol/veureRol.jsp` | Detail view | GRolController | RolDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gUsuari/veureUsuari.jsp` | Detail view | GUsuariController | UsuariDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/imports/footerStaff.jsp` | Shared layout include | (none mapped) | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/mostraProdCateg/mostraProdCateg.jsp` | Feature page | PMostraProductesCategoria | ProducteDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/shoppingCart/shoppingCart.jsp` | Shopping cart view | ShoppingCart | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gClient/veureClient.jsp` | Detail view | GClientController | ClientDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gFormaPagament/FPHome.jsp` | Home/landing view | (none mapped) | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gOfertaProducte/llistatOfertaProducte.jsp` | List view | (none mapped) | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gOfertaPunts/veureOfertaPunts.jsp` | Detail view | GOfertaPuntsController | OfertaPuntsDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gProducte/veureProducte.jsp` | Detail view | GProducteController | ProducteDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gUnitat/llistatGUnitat.jsp` | List view | (none mapped) | (none) | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/home.jsp` | Home/landing view | GProducteController, StaffController | ProducteDAO, UsuariDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/login.jsp` | Staff login screen | StaffController | UsuariDAO | Medium | 1-2 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gClient/LlistatClients.jsp` | List view | GClientController | ClientDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gFormaPagament/FPVeure.jsp` | Detail view | GFormaPagamentController | FormaPagamentDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gFoto/upldFoto.jsp` | Feature page | GFotoController | ProducteDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gOfertaComanda/llistatOfertaComanda.jsp` | List view | GOfertaComandaController | OfertaComandaDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gOfertaPunts/llistaOfertaPunts.jsp` | List view | GOfertaPuntsController | OfertaPuntsDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gPreu/llistatPreu.jsp` | List view | GPreuController | PreuDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gProducte/llistatProducte.jsp` | List view | GProducteController | ProducteDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gRol/llistatRol.jsp` | List view | GRolController | RolDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gUnitat/formGUnitat.jsp` | Form/create/update view | (none mapped) | (none) | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/gUsuari/llistatUsuari.jsp` | List view | GUsuariController | UsuariDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/staff/vistacuiner.jsp` | Feature page | GVistaCuiner, VCuiner | LiniaComandaDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/ViewMenuServlet/viewArticle.jsp` | Detail view | ViewMenuServlet | ProducteDAO, TipusProducteDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/ViewMenuServlet/viewMenu.jsp` | Detail view | ViewMenuServlet | ProducteDAO, TipusProducteDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/ViewMenuServlet/viewPlat.jsp` | Detail view | ViewMenuServlet | ProducteDAO, TipusProducteDAO | Medium | 2-3 days | Medium |
| `WebContent/WEB-INF/jsp/checkout/checkout.jsp` | Checkout flow view | CheckoutServlet | (none) | High | 2-3 days | High |
| `WebContent/WEB-INF/jsp/exemples/checkout.jsp` | Checkout flow view | (none mapped) | (none) | High | 2-3 days | High |
| `WebContent/WEB-INF/jsp/exemples/plantillaFormulari.jsp` | Form/create/update view | ExemplePublicEsborrar | UsuariDAO | High | 2-3 days | High |
| `WebContent/WEB-INF/jsp/staff/gFormaPagament/FPNovaModificar.jsp` | Form/create/update view | GFormaPagamentController | FormaPagamentDAO | High | 2-3 days | High |
| `WebContent/WEB-INF/jsp/staff/gProducte/modificaProducteCompost.jsp` | Form/create/update view | GProducteController | ProducteDAO | High | 2-3 days | High |
| `WebContent/WEB-INF/jsp/staff/gUnitat/veureUnitat.jsp` | Detail view | GUnitatController | UnitatDAO | High | 2-3 days | High |
| `WebContent/WEB-INF/jsp/staff/imports/headerStaff.jsp` | Shared layout include | (none mapped) | (none) | High | 2-3 days | High |
| `WebContent/WEB-INF/jsp/imports/headerEwok.jsp` | Feature page | (none mapped) | (none) | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gFormaPagament/FPLlistat.jsp` | List view | GFormaPagamentController | FormaPagamentDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gRol/formRol.jsp` | Form/create/update view | GRolController | RolDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gUnitat/llistatUnitat.jsp` | List view | GUnitatController | UnitatDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gUsuari/formUsuari.jsp` | Form/create/update view | GUsuariController | UsuariDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/vistaCaixer/llistatVistaCaixer.jsp` | List view | VCaixer | ComandaDAO, LiniaComandaDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/ViewMenuServlet/viewMenuServlet.jsp` | Detail view | ViewMenuServlet | ProducteDAO, TipusProducteDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gClient/formClient.jsp` | Form/create/update view | GClientController | ClientDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gOfertaComanda/formOfertaComanda.jsp` | Form/create/update view | GOfertaComandaController | OfertaComandaDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gOfertaPunts/formOfertaPunts.jsp` | Form/create/update view | GOfertaPuntsController | OfertaPuntsDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gPreu/formPreu.jsp` | Form/create/update view | GPreuController | PreuDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gProducte/formProducte.jsp` | Form/create/update view | GProducteController | ProducteDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gUnitat/formUnitat.jsp` | Form/create/update view | GUnitatController | UnitatDAO | High | 3-5 days | High |
| `WebContent/WEB-INF/jsp/staff/gOfertaProducte/formOfertaProducte.jsp` | Form/create/update view | (none mapped) | (none) | High | 3-5 days | High |
