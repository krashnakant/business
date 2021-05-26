export class ApplicationConfig {

  private readonly endpointPrefix: string;

  constructor(endpointPrefix: string) {
    this.endpointPrefix = endpointPrefix;
  }

  getEndpointFor(api: string): string {
    return `${this.endpointPrefix}${api}`;
  }
}
