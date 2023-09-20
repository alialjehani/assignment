package net;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

    /**
     *
     * Hibernate dialect for UCanAccess - identity column support
     *
     */
    public class UCanAccessDialectIdentityColumnSupport extends IdentityColumnSupportImpl {
        @Override
        public boolean supportsIdentityColumns() {
            return true;
        }

        @Override
        public boolean hasDataTypeInIdentityColumn() {
            return false;
        }

        @Override
        public String getIdentityColumnString(int type) {
            return "COUNTER";
        }

        /*
         * public boolean supportsInsertSelectIdentity() { return true; // As
         * specified in NHibernate dialect }
         */

        /*
         * public String appendIdentitySelectToInsert(String insertString) { return
         * new StringBuffer(insertString.length()+30). // As specified in NHibernate
         * dialect append(insertString).
         * append("; ").append(getIdentitySelectString()). toString(); }
         */
    }

