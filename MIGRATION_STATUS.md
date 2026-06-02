# eWok Migration Status

Tracking document for JSP → JSF/PrimeFaces (XHTML) migration.  
Source inventory: [MIGRATION_PLAN.md](MIGRATION_PLAN.md).  
Architecture reference: [Architecture.md](Architecture.md).

**Last updated:** 2026-06-02

---

## Legend

### Current status

| Status | Meaning |
|--------|---------|
| **NOT STARTED** | No XHTML/bean migration work committed for this screen |
| **IN PROGRESS** | Migration started; not yet verified end-to-end |
| **WORKING** | XHTML route verified; core behavior acceptable for current phase |
| **BLOCKED** | Cannot proceed without fixing a dependency or defect |

### Migration priority

| Priority | Meaning |
|----------|---------|
| **High** | Next recommended migrations (low risk, high learning value, or already completed pilot) |
| **Medium** | Standard backlog after High-priority screens |
| **Low** | Defer (examples, legacy discard pages, cart/checkout, layout includes, high-complexity forms) |

Priority is derived from [MIGRATION_PLAN.md](MIGRATION_PLAN.md) complexity, effort, and risk, plus migration strategy (public read-only first, staff auth/forms later).

### Complexity and effort

Copied from [MIGRATION_PLAN.md](MIGRATION_PLAN.md) (per JSP).

---

## Summary

| Metric | Count |
|--------|------:|
| Total JSP pages | 60 |
| **WORKING** | 1 |
| **IN PROGRESS** (POC, not in JSP table) | 1 (`productes.xhtml`) |
| **NOT STARTED** | 59 |

---

## Migration tracker (all JSP pages)

Sorted easiest → hardest (same order as [MIGRATION_PLAN.md](MIGRATION_PLAN.md)).  
Paths relative to `WebContent/WEB-INF/jsp/`.

| Legacy JSP | XHTML target | Complexity | Estimated effort | Migration priority | Current status |
| ---------- | ------------ | ---------- | ---------------- | ------------------ | -------------- |
| `exemples/plantilla.jsp` | — | Low | 1 day | Low | NOT STARTED |
| `home.jsp` | — | Low | 1 day | High | NOT STARTED |
| `staff/accionopermesa.jsp` | — | Medium | 1 day | Medium | NOT STARTED |
| `staff/gOfertaComanda/veureOfertaComanda.jsp` | — | Medium | 1 day | Medium | NOT STARTED |
| `staff/gUnitat/veureGUnitat.jsp` | — | Medium | 1 day | Low | NOT STARTED |
| `staff/homeClientsEsborrar.jsp` | — | Medium | 1 day | Low | NOT STARTED |
| `staff/homeProductesEsborrar.jsp` | — | Medium | 1 day | Low | NOT STARTED |
| `staff/homeUnitatEsborrar.jsp` | — | Medium | 1 day | Low | NOT STARTED |
| `exemples/home.jsp` | — | Medium | 1-2 days | Low | NOT STARTED |
| `imports/footerEwok.jsp` | — | Medium | 1-2 days | Low | NOT STARTED |
| `staff/gOfertaProducte/veureOfertaProducte.jsp` | — | Medium | 1-2 days | Medium | NOT STARTED |
| `staff/gPreu/veurePreu.jsp` | — | Medium | 1-2 days | High | NOT STARTED |
| `staff/gRol/veureRol.jsp` | — | Medium | 1-2 days | High | NOT STARTED |
| `staff/gUsuari/veureUsuari.jsp` | — | Medium | 1-2 days | High | NOT STARTED |
| `staff/imports/footerStaff.jsp` | — | Medium | 1-2 days | Low | NOT STARTED |
| `mostraProdCateg/mostraProdCateg.jsp` | `views/mostraProdCateg.xhtml` | Medium | 1-2 days | High | **WORKING** |
| `shoppingCart/shoppingCart.jsp` | — | Medium | 1-2 days | Low | NOT STARTED |
| `staff/gClient/veureClient.jsp` | — | Medium | 1-2 days | High | NOT STARTED |
| `staff/gFormaPagament/FPHome.jsp` | — | Medium | 1-2 days | Medium | NOT STARTED |
| `staff/gOfertaProducte/llistatOfertaProducte.jsp` | — | Medium | 1-2 days | Medium | NOT STARTED |
| `staff/gOfertaPunts/veureOfertaPunts.jsp` | — | Medium | 1-2 days | Medium | NOT STARTED |
| `staff/gProducte/veureProducte.jsp` | — | Medium | 1-2 days | High | NOT STARTED |
| `staff/gUnitat/llistatGUnitat.jsp` | — | Medium | 1-2 days | Medium | NOT STARTED |
| `staff/home.jsp` | — | Medium | 1-2 days | Medium | NOT STARTED |
| `staff/login.jsp` | — | Medium | 1-2 days | Medium | NOT STARTED |
| `staff/gClient/LlistatClients.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `staff/gFormaPagament/FPVeure.jsp` | — | Medium | 2-3 days | Medium | NOT STARTED |
| `staff/gFoto/upldFoto.jsp` | — | Medium | 2-3 days | Low | NOT STARTED |
| `staff/gOfertaComanda/llistatOfertaComanda.jsp` | — | Medium | 2-3 days | Medium | NOT STARTED |
| `staff/gOfertaPunts/llistaOfertaPunts.jsp` | — | Medium | 2-3 days | Medium | NOT STARTED |
| `staff/gPreu/llistatPreu.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `staff/gProducte/llistatProducte.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `staff/gRol/llistatRol.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `staff/gUnitat/formGUnitat.jsp` | — | Medium | 2-3 days | Medium | NOT STARTED |
| `staff/gUsuari/llistatUsuari.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `staff/vistacuiner.jsp` | — | Medium | 2-3 days | Low | NOT STARTED |
| `ViewMenuServlet/viewArticle.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `ViewMenuServlet/viewMenu.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `ViewMenuServlet/viewPlat.jsp` | — | Medium | 2-3 days | High | NOT STARTED |
| `checkout/checkout.jsp` | — | High | 2-3 days | Low | NOT STARTED |
| `exemples/checkout.jsp` | — | High | 2-3 days | Low | NOT STARTED |
| `exemples/plantillaFormulari.jsp` | — | High | 2-3 days | Low | NOT STARTED |
| `staff/gFormaPagament/FPNovaModificar.jsp` | — | High | 2-3 days | Medium | NOT STARTED |
| `staff/gProducte/modificaProducteCompost.jsp` | — | High | 2-3 days | Low | NOT STARTED |
| `staff/gUnitat/veureUnitat.jsp` | — | High | 2-3 days | Medium | NOT STARTED |
| `staff/imports/headerStaff.jsp` | — | High | 2-3 days | Low | NOT STARTED |
| `imports/headerEwok.jsp` | — | High | 3-5 days | Low | NOT STARTED |
| `staff/gFormaPagament/FPLlistat.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gRol/formRol.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gUnitat/llistatUnitat.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gUsuari/formUsuari.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/vistaCaixer/llistatVistaCaixer.jsp` | — | High | 3-5 days | Low | NOT STARTED |
| `ViewMenuServlet/viewMenuServlet.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gClient/formClient.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gOfertaComanda/formOfertaComanda.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gOfertaPunts/formOfertaPunts.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gPreu/formPreu.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gProducte/formProducte.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gUnitat/formUnitat.jsp` | — | High | 3-5 days | Medium | NOT STARTED |
| `staff/gOfertaProducte/formOfertaProducte.jsp` | — | High | 3-5 days | Medium | NOT STARTED |

---

## Completed migration detail

### `mostraProdCateg/mostraProdCateg.jsp` → `views/mostraProdCateg.xhtml`

| Field | Value |
|-------|--------|
| **Current status** | WORKING |
| **Complexity** | Medium |
| **Estimated effort** | 1-2 days (Phase 1 read-only delivered) |
| **Migration priority** | High (first production pilot) |
| **Bean** | `MostraProdCategBean` |
| **DAO** | `ProducteDAO.obtenirTotsXTipusIdVigents(String codi)` |
| **Test URL** | `/eWok/views/mostraProdCateg.xhtml?codi=<tipusProducteCodi>` |
| **Legacy URL** | `/eWok/mostraProdCateg?codi=...` (unchanged) |
| **Notes** | MariaDB, JSF, PrimeFaces verified. Product images pending (`PATH_FITXERS` / `GFotoController` NPE). **Blocking:** No |

---

## JSF views outside JSP inventory

| XHTML | Complexity | Estimated effort | Migration priority | Current status | Notes |
| ----- | ---------- | ---------------- | ------------------ | -------------- | ----- |
| `views/test.xhtml` | Low | 0.5 day | High | WORKING | `HomeBean` — JSF smoke test |
| `views/productes.xhtml` | Medium | 1-2 days | High | IN PROGRESS | `ProducteBean` — staff list POC; maps to `staff/gProducte/llistatProducte.jsp` |

---

## Known Technical Issues

| Issue | Impact | Blocking migration? |
|-------|--------|---------------------|
| **Hardcoded image repository path** (`PATH_FITXERS` in `GFotoController`; e.g. `/media/windows7/eWokFotos` or `E:/ewokImages`) | Product photos via `/img?accioFoto=get&idProd=...` fail or NPE in `getCurrentFile()` | No for list-only pages |
| **Legacy Jersey 1.19** (`/data/*`) | JSON APIs separate from JSF | No for JSP→XHTML |
| **Legacy JDBC DAOs** | Reuse existing DAOs; no JPA | No — by design |
| **Credentials hardcoded in `DataSourceManager`** | JDBC config in listener source | No for local dev |
| **Staff auth only on `/staff`** | JSF `*.xhtml` has no global login filter | Yes for staff parity |
| **`ProductesFiltratsService` not in Jersey scan** | REST package not registered in `web.xml` | No for JSP migration |

---

## Related documents

- [MIGRATION_PLAN.md](MIGRATION_PLAN.md) — complexity, effort, risk per JSP
- [Architecture.md](Architecture.md) — stack and layer map
- [README.md](README.md) — setup and modernization goals
