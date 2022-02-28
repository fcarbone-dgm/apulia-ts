export interface IAppConfig {
  env: {
    production: boolean
    baseUrl: string
    configUrl: string
  }

  api: {
    timesheetPath: string,
    timesheetAggregatePath: string
  }

  settings: {
    options: { name: string, value: string }[]
  }
}
