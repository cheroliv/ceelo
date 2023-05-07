import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
import duration from 'dayjs/plugin/duration';
import relativeTime from 'dayjs/plugin/relativeTime';

// jhipster-needle-i18n-language-dayjs-imports - JHipster will import languages from dayjs here
import 'dayjs/locale/en';
import 'dayjs/locale/ar-ly';
import 'dayjs/locale/zh-cn';
import 'dayjs/locale/fr';
import 'dayjs/locale/de';
import 'dayjs/locale/hi';
import 'dayjs/locale/id';
import 'dayjs/locale/ja';
import 'dayjs/locale/ru';
import 'dayjs/locale/es';

// DAYJS CONFIGURATION
dayjs.extend(customParseFormat);
dayjs.extend(duration);
dayjs.extend(relativeTime);
