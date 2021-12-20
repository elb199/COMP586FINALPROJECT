import { CentralizedLoginPage } from './app.po';

describe('centralized-login App', () => {
  let page: CentralizedLoginPage;

  beforeEach(() => {
    page = new CentralizedLoginPage();
  });

  it('message stating that the app is running', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app is running');
  });
});